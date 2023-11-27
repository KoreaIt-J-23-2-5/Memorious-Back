package com.memorious.back.dto;

import com.memorious.back.entity.ScheduleEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ScheduleReqDto {
    private int scheduleId;
    private int userId;
    private String title;
    private String labelColor;
    private String startDate;
    private String endDate;
    private int isAllDay;
    private String startTime;
    private String endTime;
    private List<String> attendee;
    private String location;
    private String repeatType;
    private String repeatCycle;
    private String repeatEndDate;
    private int repeatCount;
    private String description;

    public ScheduleEntity toEntity () {
        return ScheduleEntity.builder()
                .title(title)
                .labelColor(labelColor)
                .startDate(startDate)
                .endDate(endDate)
                .isAllDay(isAllDay)
                .startTime(startTime)
                .endTime(endTime)
                .location(location)
                .repeatType(repeatType)
                .repeatCycle(repeatCycle)
                .repeatEndDate(repeatEndDate)
                .repeatCount(repeatCount)
                .description(description)
                .userId(userId)
                .calendarScheduleId(scheduleId)
                .build();
    }
}
