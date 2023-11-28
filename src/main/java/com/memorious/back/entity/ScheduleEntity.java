package com.memorious.back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleEntity {
    private int calendarScheduleId;
    private int userId;
    private String title;
    private String labelColor;
    private String startDate;
    private String endDate;
    private int isAllDay;
    private String startTime;
    private String endTime;
    private String location;
    private String repeatType;
    private String repeatCycle;
    private String repeatEndDate;
    private int repeatCount;
    private String description;

}
