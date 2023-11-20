package com.memorious.back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarScheduleEntity {
	private int calendarScheduleId;
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


