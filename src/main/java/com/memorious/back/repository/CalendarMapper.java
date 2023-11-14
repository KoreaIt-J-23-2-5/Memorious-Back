package com.memorious.back.repository;

import com.memorious.back.entity.ScheduleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalendarMapper {
    public int insertSchedule(ScheduleEntity scheduleEntity);
}
