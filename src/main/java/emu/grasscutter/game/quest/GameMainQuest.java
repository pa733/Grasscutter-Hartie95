package emu.grasscutter.game.quest;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Indexed;
import dev.morphia.annotations.Transient;
import emu.grasscutter.Grasscutter;
import emu.grasscutter.data.GameData;
import emu.grasscutter.data.binout.MainQuestData;
import emu.grasscutter.data.binout.MainQuestData.SubQuestData;
import emu.grasscutter.data.binout.MainQuestData.TalkData;
import emu.grasscutter.data.binout.ScriptSceneData;
import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.data.excels.RewardData;
import emu.grasscutter.database.DatabaseHelper;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.props.ActionReason;
import emu.grasscutter.game.quest.enums.*;
import emu.grasscutter.net.proto.ChildQuestOuterClass.ChildQuest;
import emu.grasscutter.net.proto.ParentQuestOuterClass.ParentQuest;
import emu.grasscutter.server.packet.send.PacketCodexDataUpdateNotify;
import emu.grasscutter.server.packet.send.PacketFinishedParentQuestUpdateNotify;
import emu.grasscutter.server.packet.send.PacketQuestProgressUpdateNotify;
import emu.grasscutter.utils.Position;
import lombok.Getter;
import lombok.val;
import org.bson.types.ObjectId;

import java.util.*;

@Entity(value = "quests", useDiscriminator = false)
public class GameMainQuest {
    @Id private ObjectId id;
    @Indexed @Getter private int ownerUid;
    @Transient @Getter private Player owner;
    @Transient @Getter private QuestManager questManager;
    @Getter private Map<Integer, GameQuest> childQuests;
    @Getter private int parentQuestId;
    @Getter private int[] questVars;
    //QuestUpdateQuestVarReq is sent in two stages...
    @Getter private List<Integer> questVarsUpdate;
    @Getter private ParentQuestState state;
    @Getter private boolean isFinished;
    @Getter List<QuestGroupSuite> questGroupSuites;

    @Getter int[] suggestTrackMainQuestList;
    @Getter private Map<Integer,TalkData> talks;

    @Deprecated // Morphia only. Do not use.
    public GameMainQuest() {}

    public GameMainQuest(Player player, int parentQuestId) {
        this.owner = player;
        this.ownerUid = player.getUid();
        this.questManager = player.getQuestManager();
        this.parentQuestId = parentQuestId;
        this.childQuests = new HashMap<>();
        this.talks = new HashMap<>();
        //official server always has a list of 5 questVars, with default value 0
        this.questVars = new int[] {0,0,0,0,0};
        this.state = ParentQuestState.PARENT_QUEST_STATE_NONE;
        this.questGroupSuites = new ArrayList<>();
        addAllChildQuests();
    }

    private void addAllChildQuests() {
        List<Integer> subQuestIds = Arrays.stream(GameData.getMainQuestDataMap().get(this.parentQuestId).getSubQuests()).map(SubQuestData::getSubId).toList();
        for (Integer subQuestId : subQuestIds) {
            QuestData questConfig = GameData.getQuestDataMap().get(subQuestId);
            this.childQuests.put(subQuestId, new GameQuest(this, questConfig));
        }
    }

    public Collection<GameQuest> getActiveQuests(){
        return childQuests.values().stream()
            .filter(q->q.getState().getValue() == QuestState.UNFINISHED.getValue())
            .toList();
    }

    public void setOwner(Player player) {
        if (player.getUid() != this.getOwnerUid()) return;
        this.owner = player;
    }

    public int getQuestVar(int i) {
        return questVars[i];
    }
    public void setQuestVar(int i, int value) {
        int previousValue = this.questVars[i];
        this.questVars[i] = value;
        Grasscutter.getLogger().debug("questVar {} value changed from {} to {}", i, previousValue, value);
    }

    public void incQuestVar(int i, int inc) {
        int previousValue = this.questVars[i];
        this.questVars[i] += inc;
        Grasscutter.getLogger().debug("questVar {} value incremented from {} to {}", i, previousValue, previousValue + inc);
    }

    public void decQuestVar(int i, int dec) {
        int previousValue = this.questVars[i];
        this.questVars[i] -= dec;
        Grasscutter.getLogger().debug("questVar {} value decremented from {} to {}", i, previousValue, previousValue - dec);
    }


    public GameQuest getChildQuestById(int id) {
        return this.getChildQuests().get(id);
    }
    public GameQuest getChildQuestByOrder(int order) {
        return this.getChildQuests().values().stream().filter(p -> p.getQuestData().getOrder() == order).toList().get(0);
    }

    public void finish() {
        // Avoid recursion from child finish() in GameQuest
        // when auto finishing all child quests with QUEST_STATE_UNFINISHED (below)
        if (this.isFinished) {
            Grasscutter.getLogger().debug("Skip main quest finishing because it's already finished");
            return;
        }

        this.isFinished = true;
        this.state = ParentQuestState.PARENT_QUEST_STATE_FINISHED;

         /*
            We also need to check for unfinished childQuests in this MainQuest
            force them to complete and send a packet about this to the user,
            because at some points there are special "invisible" child quests that control
            some situations.

            For example, subQuest 35312 is responsible for the event of leaving the territory
            of the island with a statue and automatically returns the character back,
            quest 35311 completes the main quest line 353 and starts 35501 from
            new MainQuest 355 but if 35312 is not completed after the completion
            of the main quest 353 - the character will not be able to leave place
            (return again and again)
            */
        this
            .getChildQuests()
            .values()
            .stream()
            .filter(p -> p.state != QuestState.QUEST_STATE_FINISHED)
            .forEach(GameQuest::finish);

        this.getOwner().getSession().send(new PacketFinishedParentQuestUpdateNotify(this));
        this.getOwner().getSession().send(new PacketCodexDataUpdateNotify(this));

        this.save();

        // Add rewards
        MainQuestData mainQuestData = GameData.getMainQuestDataMap().get(this.getParentQuestId());
        if(mainQuestData.getRewardIdList()!=null) {
            for (int rewardId : mainQuestData.getRewardIdList()) {
                RewardData rewardData = GameData.getRewardDataMap().get(rewardId);

                if (rewardData == null) {
                    continue;
                }

                getOwner().getInventory().addItemParamDatas(rewardData.getRewardItemList(), ActionReason.QuestReward);
            }
        }

        // handoff main quest
        if (mainQuestData.getSuggestTrackMainQuestList() != null) {
            Arrays.stream(mainQuestData.getSuggestTrackMainQuestList())
                .forEach(getQuestManager()::startMainQuest);
        }
    }
    //TODO
    public void fail() {}
    public void cancel() {}


    public List<Position> rewindTo(GameQuest targetQuest, boolean notifyDelete){
        if(targetQuest == null || !targetQuest.rewind(notifyDelete)){
            return null;
        }

        /*if(rewindPositions.isEmpty()){
            addRewindPoints();
        }*/

        List<Position> posAndRot = new ArrayList<>();
        if(hasRewindPosition(targetQuest.getSubQuestId(), posAndRot)){
            return posAndRot;
        }

        List<GameQuest> rewindQuests = getChildQuests().values().stream()
            .filter(p -> (p.getState() == QuestState.QUEST_STATE_UNFINISHED || p.getState() == QuestState.QUEST_STATE_FINISHED) &&
                p.getQuestData() != null && p.getQuestData().isRewind()).toList();

        for (GameQuest quest : rewindQuests) {
            if (hasRewindPosition(quest.getSubQuestId(), posAndRot)) {
                return posAndRot;
            }

        }
        return null;
    }

    // Rewinds to the last finished/unfinished rewind quest, and returns the avatar rewind position (if it exists)
    public List<Position> rewind() {
        if (this.questManager == null) {
            this.questManager = getOwner().getQuestManager();
        }
        var activeQuests = getActiveQuests();
        var highestActiveQuest = activeQuests.stream()
            .filter(q -> q.getQuestData() != null)
            .max(Comparator.comparing(q -> q.getQuestData().getOrder()))
            .orElse(null);

        if (highestActiveQuest == null) {
            var firstUnstarted = getChildQuests().values().stream()
                .filter(q -> q.getQuestData() != null && q.getState().getValue() != QuestState.FINISHED.getValue())
                .min(Comparator.comparingInt(a -> a.getQuestData().getOrder()));
            if(firstUnstarted.isEmpty()){
                // all quests are probably finished, do don't rewind and maybe also set the mainquest to finished?
                return null;
            }
            highestActiveQuest = firstUnstarted.get();
            //todo maybe try to accept quests if there is no active quest and no rewind target?
            //tryAcceptSubQuests(QuestTrigger.QUEST_COND_NONE, "", 0);
        }

        var highestOrder = highestActiveQuest.getQuestData().getOrder();
        var rewindTarget = getChildQuests().values().stream()
            .filter(q -> q.getQuestData() != null)
            .filter(q -> q.getQuestData().isRewind() && q.getQuestData().getOrder() <= highestOrder)
            .max(Comparator.comparingInt(a -> a.getQuestData().getOrder()))
            .orElse(null);

        return rewindTo(rewindTarget!=null? rewindTarget : highestActiveQuest, false);
    }

    public boolean hasRewindPosition(int subId, List<Position> posAndRot){
        RewindData questRewind = GameData.getRewindDataMap().get(subId);
        if (questRewind == null) return false;

        RewindData.AvatarData avatarData = questRewind.getAvatar();
        if (avatarData == null) return false;

        String avatarPos = avatarData.getPos();
        QuestData.Guide guide = GameData.getQuestDataMap().get(subId).getGuide();
        if (guide == null) return false;

        int sceneId = guide.getGuideScene();
        ScriptSceneData fullGlobals = GameData.getScriptSceneDataMap().get("flat.luas.scenes.full_globals.lua.json");
        if (fullGlobals == null) return false;

        ScriptSceneData.ScriptObject dummyPointScript = fullGlobals.getScriptObjectList().get(sceneId + "/scene" + sceneId + "_dummy_points.lua");
        if (dummyPointScript == null) return false;

        Map<String, List<Float>> dummyPointMap = dummyPointScript.getDummyPoints();
        if (dummyPointMap == null) return false;

        List<Float> avatarPosPos = dummyPointMap.get(avatarPos + ".pos");
        List<Float> avatarPosRot = dummyPointMap.get(avatarPos + ".rot");
        if (avatarPosPos == null) return false;

        posAndRot.add(0, new Position(avatarPosPos.get(0),avatarPosPos.get(1),avatarPosPos.get(2))); // position
        posAndRot.add(1, new Position(avatarPosRot.get(0),avatarPosRot.get(1),avatarPosRot.get(2))); //rotation
        Grasscutter.getLogger().info("Succesfully loaded rewind data for subQuest {}", subId);
        return true;
    }

    public boolean hasTeleportPostion(int subId, List<Position> posAndRot){
        TeleportData questTransmit = GameData.getTeleportDataMap().get(subId);
        if (questTransmit == null) return false;

        TeleportData.TransmitPoint transmitPoint = questTransmit.getTransmit_points().size() > 0 ? questTransmit.getTransmit_points().get(0) : null;
        if (transmitPoint == null) return false;

        String transmitPos = transmitPoint.getPos();
        int sceneId = transmitPoint.getScene_id();
        ScriptSceneData fullGlobals = GameData.getScriptSceneDataMap().get("flat.luas.scenes.full_globals.lua.json");
        if (fullGlobals == null) return false;

        ScriptSceneData.ScriptObject dummyPointScript = fullGlobals.getScriptObjectList().get(sceneId + "/scene" + sceneId + "_dummy_points.lua");
        if (dummyPointScript == null) return false;

        Map<String, List<Float>> dummyPointMap = dummyPointScript.getDummyPoints();
        if (dummyPointMap == null) return false;

        List<Float> transmitPosPos = dummyPointMap.get(transmitPos + ".pos");
        List<Float> transmitPosRot = dummyPointMap.get(transmitPos + ".rot");
        if (transmitPosPos == null) return false;

        posAndRot.add(0, new Position(transmitPosPos.get(0), transmitPosPos.get(1), transmitPosPos.get(2))); // position
        posAndRot.add(1, new Position(transmitPosRot.get(0), transmitPosRot.get(1), transmitPosRot.get(2))); // rotation
        Grasscutter.getLogger().info("Succesfully loaded teleport data for subQuest {}", subId);
        return true;
    }

    public void checkProgress(){
        for (var quest : getChildQuests().values()){
            if(quest.getState() == QuestState.QUEST_STATE_UNFINISHED) {
                questManager.checkQuestAlreadyFullfilled(quest);
            }
        }
    }

    public void tryAcceptSubQuests(QuestCond condType, String paramStr, int... params) {
        try {
            List<GameQuest> subQuestsWithCond = getChildQuests().values().stream()
                .filter(p -> p.getState() == QuestState.QUEST_STATE_UNSTARTED || p.getState() == QuestState.UNFINISHED)
                .filter(p -> p.getQuestData().getAcceptCond().stream().anyMatch(q -> condType == QuestCond.QUEST_COND_NONE || q.getType() == condType))
                .toList();

            for (GameQuest subQuestWithCond : subQuestsWithCond) {
                val acceptCond = subQuestWithCond.getQuestData().getAcceptCond();
                int[] accept = new int[acceptCond.size()];

                for (int i = 0; i < subQuestWithCond.getQuestData().getAcceptCond().size(); i++) {
                    val condition = acceptCond.get(i);
                    boolean result = this.getOwner().getServer().getQuestSystem().triggerCondition(subQuestWithCond, condition, paramStr, params);
                    accept[i] = result ? 1 : 0;
                }

                boolean shouldAccept = LogicType.calculate(subQuestWithCond.getQuestData().getAcceptCondComb(), accept);

                if (shouldAccept)
                    subQuestWithCond.start();
            }
            this.save();
        } catch (Exception e) {
            Grasscutter.getLogger().error("An error occurred while trying to accept quest.", e);
        }

    }

    public void tryFailSubQuests(QuestContent condType, String paramStr, int... params) {
        try {
            List<GameQuest> subQuestsWithCond = getChildQuests().values().stream()
                .filter(p -> p.getState() == QuestState.QUEST_STATE_UNFINISHED)
                .filter(p -> p.getQuestData().getFailCond().stream().anyMatch(q -> q.getType() == condType))
                .toList();

            for (GameQuest subQuestWithCond : subQuestsWithCond) {
                val failCond = subQuestWithCond.getQuestData().getFailCond();

                for (int i = 0; i < subQuestWithCond.getQuestData().getFailCond().size(); i++) {
                    val condition = failCond.get(i);
                    if (condition.getType() == condType) {
                        boolean result = this.getOwner().getServer().getQuestSystem().triggerContent(subQuestWithCond, condition, paramStr, params);
                        subQuestWithCond.getFailProgressList()[i] = result ? 1 : 0;
                        if (result) {
                            getOwner().getSession().send(new PacketQuestProgressUpdateNotify(subQuestWithCond));
                        }
                    }
                }

                boolean shouldFail = LogicType.calculate(subQuestWithCond.getQuestData().getFailCondComb(), subQuestWithCond.getFailProgressList());

                if (shouldFail)
                    subQuestWithCond.fail();
            }

        } catch (Exception e) {
            Grasscutter.getLogger().error("An error occurred while trying to fail quest.", e);
        }
    }

    public void tryFinishSubQuests(QuestContent condType, String paramStr, int... params) {
        try {
            List<GameQuest> subQuestsWithCond = getChildQuests().values().stream()
                //There are subQuests with no acceptCond, but can be finished (example: 35104)
                .filter(p -> p.getState() == QuestState.QUEST_STATE_UNFINISHED && p.getQuestData().getAcceptCond() != null)
                .filter(p -> p.getQuestData().getFinishCond().stream().anyMatch(q -> q.getType() == condType))
                .toList();

            for (GameQuest subQuestWithCond : subQuestsWithCond) {
                val finishCond = subQuestWithCond.getQuestData().getFinishCond();

                for (int i = 0; i < finishCond.size(); i++) {
                    val condition = finishCond.get(i);
                    if (condition.getType() == condType) {
                        boolean result = this.getOwner().getServer().getQuestSystem().triggerContent(subQuestWithCond, condition, paramStr, params);
                        subQuestWithCond.getFinishProgressList()[i] = result ? 1 : 0;
                        if (result) {
                            getOwner().getSession().send(new PacketQuestProgressUpdateNotify(subQuestWithCond));
                        }
                    }
                }

                boolean shouldFinish = LogicType.calculate(subQuestWithCond.getQuestData().getFinishCondComb(), subQuestWithCond.getFinishProgressList());

                if (shouldFinish)
                    subQuestWithCond.finish();
            }
        } catch (Exception e) {
            Grasscutter.getLogger().debug("An error occurred while trying to finish quest.", e);
        }
    }

    public void save() {
        DatabaseHelper.saveQuest(this);
    }

    public void delete() {
        DatabaseHelper.deleteQuest(this);
    }

    public ParentQuest toProto(boolean withChildQuests) {
        ParentQuest.Builder proto = ParentQuest.newBuilder()
                .setParentQuestId(getParentQuestId())
                .setIsFinished(isFinished())
                .setParentQuestState(getState().getValue())
                .setCutsceneEncryptionKey(QuestManager.getQuestKey(parentQuestId));
        if(withChildQuests) {
            for (GameQuest quest : this.getChildQuests().values()) {
                if (quest.getState() != QuestState.QUEST_STATE_UNSTARTED) {
                    ChildQuest childQuest = ChildQuest.newBuilder()
                        .setQuestId(quest.getSubQuestId())
                        .setState(quest.getState().getValue())
                        .build();

                    proto.addChildQuestList(childQuest);
                }
            }
        }

        for (int i : getQuestVars()) {
            proto.addQuestVar(i);
        }


        return proto.build();
    }

}
