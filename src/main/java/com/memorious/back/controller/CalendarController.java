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

    @PutMapping("/calendar/schedule/{scheduleId}")
    public ResponseEntity<?> updateSchedule(@RequestBody ScheduleReqDto scheduleReqDto, @PathVariable int scheduleId) {
        return ResponseEntity.ok(calendarService.updateSchedule(scheduleReqDto, scheduleId));
    }

    @DeleteMapping("/calendar/schedule/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable int scheduleId) {
        return ResponseEntity.ok(calendarService.deleteSchedule(scheduleId));
    }
}
