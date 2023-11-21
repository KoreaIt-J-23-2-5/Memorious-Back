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

    @GetMapping("/calendar/{year}/{month}")
    public ResponseEntity<?> getSchedule(
            @PathVariable("year") String year,
            @PathVariable("month") String month) {

        System.out.println("찍히나요");
        System.out.println(year);
        System.out.println(month);


        return ResponseEntity.ok(calendarService.getYMSchedule(year, month));
    }
}
