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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarMapper calendarMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean addSchedule(ScheduleReqDto scheduleReqDto) {
        ScheduleEntity scheduleEntity = scheduleReqDto.toEntity();
        if(scheduleReqDto.getAttendee() == null || scheduleReqDto.getAttendee().toArray().length == 0) {
            return calendarMapper.insertSchedule(scheduleEntity) > 0;
        }
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

}
