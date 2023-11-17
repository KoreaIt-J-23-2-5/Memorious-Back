package com.memorious.back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarAttendeeEntity {
    private int calendarScheduleId;
    private int userId;

    // Getter and Setter methods
}
