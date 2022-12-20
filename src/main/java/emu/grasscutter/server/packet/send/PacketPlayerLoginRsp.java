package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.PlayerLoginRspOuterClass.PlayerLoginRsp;
import emu.grasscutter.net.proto.QueryCurrRegionHttpRspOuterClass;
import emu.grasscutter.server.http.dispatch.DispatchHandler;

public class PacketPlayerLoginRsp extends BasePacket {

    private static QueryCurrRegionHttpRspOuterClass.QueryCurrRegionHttpRsp regionCache;

    public PacketPlayerLoginRsp(String gameVersion) {
        super(PacketOpcodes.PlayerLoginRsp, 1);

        this.setUseDispatchKey(true);

        var info = DispatchHandler.getCurrentRegion(gameVersion).getRegionInfo();

        PlayerLoginRsp p = PlayerLoginRsp.newBuilder()
                .setIsUseAbilityHash(true) // true
                .setAbilityHashCode(1844674) // 1844674
                .setGameBiz("hk4e_global")
                .setClientDataVersion(info.getClientDataVersion())
                .setClientSilenceDataVersion(info.getClientSilenceDataVersion())
                .setClientMd5(info.getClientDataMd5())
                .setClientSilenceMd5(info.getClientSilenceDataMd5())
                .setResVersionConfig(info.getResVersionConfig())
                .setClientVersionSuffix(info.getClientVersionSuffix())
                .setClientSilenceVersionSuffix(info.getClientSilenceVersionSuffix())
                .setIsScOpen(false)
                //.setScInfo(ByteString.copyFrom(new byte[] {}))
                .setRegisterCps("mihoyo")
                .setCountryCode("US")
                .build();

        this.setData(p.toByteArray());
    }
}
