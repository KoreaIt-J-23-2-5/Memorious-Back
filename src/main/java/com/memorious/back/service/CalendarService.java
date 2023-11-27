package com.memorious.back.service;

import com.memorious.back.dto.CalendarRespDto;
import com.memorious.back.dto.ScheduleReqDto;
import com.memorious.back.entity.CalendarScheduleEntity;
import com.memorious.back.entity.ScheduleEntity;
import com.memorious.back.entity.User;
import com.memorious.back.method.CalendarRepeatUtil;
import com.memorious.back.repository.CalendarMapper;
import com.memorious.back.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import net.minidev.json.writer.UpdaterMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarMapper calendarMapper;
    private final CalendarRepeatUtil calendarRepeatUtil;

    @Transactional(rollbackFor = Exception.class)
    public boolean addSchedule(ScheduleReqDto scheduleReqDto) {
        ScheduleEntity scheduleEntity = scheduleReqDto.toEntity();

        System.out.println(scheduleReqDto);

        String repeatType = scheduleReqDto.getRepeatCycle();
        String repeatEndType = scheduleReqDto.getRepeatType();
        int repeatCount = scheduleReqDto.getRepeatCount();
        int repeatLimitYear = 5;

        String startDate = scheduleReqDto.getStartDate();
        LocalDate startDateObj = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);

        String endDate = scheduleReqDto.getEndDate();
        LocalDate endDateObj = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);

        switch (repeatType) {
            case "":
                return calendarRepeatUtil.addSchedule(scheduleReqDto);

            case "month":
                switch (repeatEndType) {
                    case "none":
                        //for : 1개월 주기로 repeatCount 만큼 반복
                        repeatCount = 12 * repeatLimitYear;
                        return calendarRepeatUtil.monthRepeatCount(scheduleReqDto, repeatCount);
                    case "count":
                        repeatCount = scheduleReqDto.getRepeatCount();
                        return calendarRepeatUtil.monthRepeatCount(scheduleReqDto, repeatCount);
                    case "date":
                        //for : 1개월 주기로 repeatCount회 만큼
                        String repeatEndDate = scheduleReqDto.getRepeatEndDate();
                        LocalDate repeatEndDateObj = LocalDate.parse(repeatEndDate, DateTimeFormatter.ISO_LOCAL_DATE);
                        return calendarRepeatUtil.monthRepeatDate(scheduleReqDto);
                    default: return false;
                }
            case "year":
                switch (repeatEndType) {
                    case "none":
                        //for : 1년주기로 n회 반복
                        repeatCount = repeatLimitYear;
                        return calendarRepeatUtil.yearRepeatCount(scheduleReqDto, repeatCount);

                    case "count":
                        //for : 1년 주기로 repeatCount회 만큼
                        return calendarRepeatUtil.yearRepeatCount(scheduleReqDto, repeatCount);

                    case "date":
                        //while : repeatEndDate 전까지 1년마다 반복
                        return calendarRepeatUtil.yearRepeatDate(scheduleReqDto);
                    default: return false;
                }
                // cycle이 숫자인 경우
                default:
                    switch (repeatType) {
                        case "day":
                            repeatType = "1";
                            break;
                        case "week":
                            repeatType = "7";
                            break;
                        default:
                            break;
                    }
                    int repeatCycle = Integer.parseInt(repeatType);

                    switch (repeatEndType) {
                        case "none":
                            System.out.println("none3");
                            LocalDate lastDate = startDateObj.plusYears(repeatLimitYear);
                            return calendarRepeatUtil.dayRepeatDate(scheduleReqDto, lastDate, repeatCycle);

                        case "date":
                            System.out.println("date3");
                            //while : endDate 전까지 cycle 주기로 반복
                            String repeatEndDate = scheduleReqDto.getRepeatEndDate();
                            LocalDate repeatEndDateObj = LocalDate.parse(repeatEndDate, DateTimeFormatter.ISO_LOCAL_DATE);
                            return calendarRepeatUtil.dayRepeatDate(scheduleReqDto, repeatEndDateObj, repeatCycle);

                        case "count":
                            System.out.println("count3");
                            //for : cycle주기로 repeatCount회 만큼
                            return calendarRepeatUtil.dayRepeatCount(scheduleReqDto, repeatCount, repeatCycle);
                        default: return false;
                    }
        }
    }

        @Transactional(rollbackFor = Exception.class)
        public List<CalendarScheduleEntity> getMonthlySchedule (String month){
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = principalUser.getUser();
            int familyId = user.getFamilyId();

            List<CalendarScheduleEntity> data = calendarMapper.getMonthData(familyId, month);
            return data;
        }

        @Transactional(rollbackFor = Exception.class)
        public boolean updateSchedule (ScheduleReqDto scheduleReqDto,int scheduleId){
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
        public boolean deleteSchedule ( int scheduleId){
            calendarMapper.deleteAttendee(scheduleId);
            return calendarMapper.deleteSchedule(scheduleId) > 0;

        }
    }
