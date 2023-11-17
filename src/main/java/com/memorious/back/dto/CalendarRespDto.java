package com.memorious.back.dto;

import com.memorious.back.entity.CalendarAttendeeEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CalendarRespDto {
    private int userId;
    private String title;
    private String labelColor;
    private String startDate;
    private String endDate;
    private boolean isAllDay;
    private String startTime;
    private String endTime;
    private String location;
    private String repeatType;
    private String repeatCycle;
    private String repeatEndDate;
    private String repeatCount;
    private String description;
    private List<CalendarAttendeeEntity> attendees;
}
