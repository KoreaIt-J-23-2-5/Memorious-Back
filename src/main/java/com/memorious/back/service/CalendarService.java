package com.memorious.back.service;

import com.memorious.back.dto.ScheduleReqDto;
import com.memorious.back.entity.ScheduleEntity;
import com.memorious.back.repository.CalendarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarMapper calendarMapper;

    public boolean addSchedule(ScheduleReqDto scheduleReqDto) {
        ScheduleEntity scheduleEntity = scheduleReqDto.toEntity();
        System.out.println(scheduleReqDto);
        return calendarMapper.insertSchedule(scheduleEntity) > 0;
    }

}
