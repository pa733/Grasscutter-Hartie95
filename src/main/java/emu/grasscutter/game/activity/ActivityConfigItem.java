package emu.grasscutter.game.activity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityConfigItem {
    int activityId;
    int activityType;
    int scheduleId;
    List<Integer> meetCondList;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date beginTime;
    Date endTime;

    transient ActivityHandler activityHandler;
}
