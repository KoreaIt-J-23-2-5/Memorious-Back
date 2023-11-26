package com.memorious.back.controller;

import com.memorious.back.dto.ScheduleReqDto;
import com.memorious.back.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping("/calendar/schedule")
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleReqDto scheduleReqDto) {
        return ResponseEntity.ok(calendarService.addSchedule(scheduleReqDto));
    }

    @GetMapping("/calendar/schedule/{month}")
    public ResponseEntity<?> getSchedule(@PathVariable String month) {

        return ResponseEntity.ok(calendarService.getMonthlySchedule(month));
    }
}
