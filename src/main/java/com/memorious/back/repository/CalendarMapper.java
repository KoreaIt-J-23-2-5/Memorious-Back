package com.memorious.back.repository;

import com.memorious.back.dto.CalendarRespDto;
import com.memorious.back.entity.CalendarScheduleEntity;
import com.memorious.back.entity.ScheduleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CalendarMapper {
    public int insertSchedule(ScheduleEntity scheduleEntity);
    public int insertAttendee(Map<String, Object> map);
    public List<CalendarScheduleEntity> getMonthData(int familyId, String month);
    public int updateSchedule(ScheduleEntity scheduleEntity);
    public int deleteAttendee(int scheduleId);
    public int deleteSchedule(int scheduleId);
}
