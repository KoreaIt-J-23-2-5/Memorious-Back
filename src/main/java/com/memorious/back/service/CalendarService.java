package com.memorious.back.service;

import com.memorious.back.dto.CalendarRespDto;
import com.memorious.back.dto.ScheduleReqDto;
import com.memorious.back.entity.CalendarScheduleEntity;
import com.memorious.back.entity.ScheduleEntity;
import com.memorious.back.entity.User;
import com.memorious.back.repository.CalendarMapper;
import com.memorious.back.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import net.minidev.json.writer.UpdaterMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarMapper calendarMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean addSchedule(ScheduleReqDto scheduleReqDto) {
        System.out.println("scheduleReqDto = " + scheduleReqDto);;
        ScheduleEntity scheduleEntity = scheduleReqDto.toEntity();
        System.out.println("scheduleEntity = " + scheduleEntity);
        // 참석자가 없으면 바로 insert
        if(scheduleReqDto.getAttendee() == null || scheduleReqDto.getAttendee().toArray().length == 0) {
            return calendarMapper.insertSchedule(scheduleEntity) > 0;
        }

        // 참석자가 있다면 일정 따로, 참석자(반복) 따로 insert
        calendarMapper.insertSchedule(scheduleEntity);
        Map<String, Object> map = new HashMap<>();
        map.put("calendarScheduleId", scheduleEntity.getCalendarScheduleId());
        map.put("userIdList", scheduleReqDto.getAttendee());
        return calendarMapper.insertAttendee(map) > 0;


        
    }

    @Transactional(rollbackFor = Exception.class)
    public List<CalendarScheduleEntity> getMonthlySchedule(String month) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principalUser.getUser();
        int familyId = user.getFamilyId();

        List<CalendarScheduleEntity> data = calendarMapper.getMonthData(familyId, month);
        return data;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateSchedule(ScheduleReqDto scheduleReqDto, int scheduleId) {
        ScheduleEntity scheduleEntity = scheduleReqDto.toEntity();
        scheduleEntity.builder()
                .calendarScheduleId(scheduleId)
                .build();

        if (scheduleReqDto.getAttendee() == null || scheduleReqDto.getAttendee().toArray().length == 0) {
            // Attendee가 없는 경우 스케줄 정보만 업데이트
            return calendarMapper.updateSchedule(scheduleEntity) > 0;
        }

        calendarMapper.updateSchedule(scheduleEntity);

        Map<String, Object> map = new HashMap<>();
        map.put("calendarScheduleId", scheduleId);
        map.put("userIdList", scheduleReqDto.getAttendee());

        // 기존 Attendee 정보 삭제 후 새로운 Attendee 정보 추가
        calendarMapper.deleteAttendee(scheduleId);
        return calendarMapper.insertAttendee(map) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteSchedule(int scheduleId) {
        calendarMapper.deleteAttendee(scheduleId);
        return calendarMapper.deleteSchedule(scheduleId) > 0;

    }
}
