package com.memorious.back.service;

import com.memorious.back.dto.CalendarRespDto;
import com.memorious.back.dto.ScheduleReqDto;
import com.memorious.back.entity.ScheduleEntity;
import com.memorious.back.repository.CalendarMapper;
import lombok.RequiredArgsConstructor;
import net.minidev.json.writer.UpdaterMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarMapper calendarMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean addSchedule(ScheduleReqDto scheduleReqDto) {
        System.out.println(scheduleReqDto);
        ScheduleEntity scheduleEntity = scheduleReqDto.toEntity();
        if(scheduleReqDto.getAttendee() == null || scheduleReqDto.getAttendee().toArray().length == 0) {
            return calendarMapper.insertSchedule(scheduleEntity) > 0;
        }
        calendarMapper.insertSchedule(scheduleEntity);
        Map<String, Object> map = new HashMap<>();
        map.put("calendarScheduleId", scheduleEntity.getCalendarScheduleId());
        map.put("userIdList", scheduleReqDto.getAttendee());
        System.out.println(map);
        return calendarMapper.insertAttendee(map) > 0;
    }

    public CalendarRespDto getYMSchedule(String year, String month) {
        Map<String, String> dateMap = new HashMap<>();
        dateMap.put("year", year);
        dateMap.put("month", month);


        return calendarMapper.getMonthData(dateMap);
    }

}
