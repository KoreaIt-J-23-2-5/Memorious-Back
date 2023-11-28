package com.memorious.back.method;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.memorious.back.dto.ScheduleReqDto;
import com.memorious.back.entity.ScheduleEntity;
import com.memorious.back.repository.CalendarMapper;
import com.memorious.back.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CalendarRepeatUtil {

    private final CalendarMapper calendarMapper;

    private boolean processScheduleWithAttendees(ScheduleEntity scheduleEntity, ScheduleReqDto scheduleReqDto) {
        calendarMapper.insertSchedule(scheduleEntity);
        Map<String, Object> map = new HashMap<>();
        map.put("calendarScheduleId", scheduleEntity.getCalendarScheduleId());
        map.put("userIdList", scheduleReqDto.getAttendee());
        return calendarMapper.insertAttendee(map) > 0;
    }

    // 메서드에서 사용할 조건을 분리하고 메서드 호출로 대체
    public boolean addSchedule(ScheduleReqDto scheduleReqDto) {
        ScheduleEntity scheduleEntity = scheduleReqDto.toEntity();
        // 참석자 여부에 따라 처리
        if (hasAttendees(scheduleReqDto)) {
            return processScheduleWithAttendees(scheduleEntity, scheduleReqDto);
        } else {
            return calendarMapper.insertSchedule(scheduleEntity) > 0;
        }
    }

    // 참석자가 있는지 여부를 확인하는 메서드
    private boolean hasAttendees(ScheduleReqDto scheduleReqDto) {
        return scheduleReqDto.getAttendee() != null && scheduleReqDto.getAttendee().toArray().length > 0;
    }

    public boolean monthRepeatCount(ScheduleReqDto scheduleReqDto, int repeatCount) {
        String startDate = scheduleReqDto.getStartDate();
        LocalDate startDateObj = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        String endDate = scheduleReqDto.getEndDate();
        LocalDate endDateObj = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);

        for(int i=0; i < repeatCount; i++ ) {
            LocalDate addYearStartDate = startDateObj.plusMonths(i);
            scheduleReqDto.setStartDate(addYearStartDate.toString());
            LocalDate addYearEndDate = endDateObj.plusMonths(i);
            scheduleReqDto.setEndDate(addYearEndDate.toString());
            addSchedule(scheduleReqDto);
        }
        return true;
    }
    public boolean monthRepeatDate(ScheduleReqDto scheduleReqDto) {
        String startDate = scheduleReqDto.getStartDate();
        LocalDate startDateObj = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        String endDate = scheduleReqDto.getEndDate();
        LocalDate endDateObj = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        String repeatEndDate = scheduleReqDto.getRepeatEndDate();
        LocalDate repeatEndDateObj = LocalDate.parse(repeatEndDate, DateTimeFormatter.ISO_LOCAL_DATE);

        int i = 0;
        while (startDateObj.isBefore(repeatEndDateObj)) {
            startDateObj = startDateObj.plusMonths(i);
            scheduleReqDto.setStartDate(startDateObj.toString());
            endDateObj = endDateObj.plusMonths(i);
            scheduleReqDto.setEndDate(endDateObj.toString());
            addSchedule(scheduleReqDto);
            i++;
        }
        return true;
    }

    public boolean yearRepeatCount(ScheduleReqDto scheduleReqDto, int repeatCount) {
        String startDate = scheduleReqDto.getStartDate();
        LocalDate startDateObj = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        String endDate = scheduleReqDto.getEndDate();
        LocalDate endDateObj = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);

        for(int i=0; i < repeatCount; i++ ) {
            LocalDate addYearStartDate = startDateObj.plusYears(i);
            scheduleReqDto.setStartDate(addYearStartDate.toString());
            LocalDate addYearEndDate = endDateObj.plusYears(i);
            scheduleReqDto.setEndDate(addYearEndDate.toString());
            addSchedule(scheduleReqDto);
        }
        return true;
    }

    public boolean yearRepeatDate(ScheduleReqDto scheduleReqDto) {
        String startDate = scheduleReqDto.getStartDate();
        LocalDate startDateObj = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        String endDate = scheduleReqDto.getEndDate();
        LocalDate endDateObj = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        String repeatEndDate = scheduleReqDto.getRepeatEndDate();
        LocalDate repeatEndDateObj = LocalDate.parse(repeatEndDate, DateTimeFormatter.ISO_LOCAL_DATE);

        int i = 0;
        while (startDateObj.isBefore(repeatEndDateObj)) {
            startDateObj = startDateObj.plusYears(i);
            scheduleReqDto.setStartDate(startDateObj.toString());
            endDateObj = endDateObj.plusYears(i);
            scheduleReqDto.setEndDate(endDateObj.toString());
            addSchedule(scheduleReqDto);
            i++;
        }
        return true;
    }

    public boolean dayRepeatDate(ScheduleReqDto scheduleReqDto, LocalDate lastDate, int repeatCycle) {
        String startDate = scheduleReqDto.getStartDate();
        LocalDate startDateObj = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        String endDate = scheduleReqDto.getEndDate();
        LocalDate endDateObj = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);

        addSchedule(scheduleReqDto);

        while(startDateObj.isBefore(lastDate)) {
            startDateObj = startDateObj.plusDays( repeatCycle);
            scheduleReqDto.setStartDate(startDateObj.toString());
            endDateObj = endDateObj.plusDays(repeatCycle);
            scheduleReqDto.setEndDate(endDateObj.toString());
            addSchedule(scheduleReqDto);
        }
        return true;
    }

    public boolean dayRepeatCount(ScheduleReqDto scheduleReqDto, int repeatCount, int repeatCycle) {
        String startDate = scheduleReqDto.getStartDate();
        LocalDate startDateObj = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        String endDate = scheduleReqDto.getEndDate();
        LocalDate endDateObj = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);

        for(int i=0; i< repeatCount; i++) {
            LocalDate addDaysToStart = startDateObj.plusDays( i * repeatCycle);
            LocalDate addDaysToEnd = endDateObj.plusDays( i * repeatCycle);
            System.out.println(addDaysToStart);
            scheduleReqDto.setStartDate(addDaysToStart.toString());
            scheduleReqDto.setEndDate(addDaysToEnd.toString());
            addSchedule(scheduleReqDto);
        }
        return true;
    }
}
