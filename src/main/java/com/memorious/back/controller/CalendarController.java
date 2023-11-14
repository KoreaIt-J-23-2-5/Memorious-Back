package com.memorious.back.controller;

import com.memorious.back.dto.ScheduleReqDto;
import com.memorious.back.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping("/calendar/schedule")
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleReqDto scheduleReqDto) {
        return ResponseEntity.ok(calendarService.addSchedule(scheduleReqDto));
    }
}
