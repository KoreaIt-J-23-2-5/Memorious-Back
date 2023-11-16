package com.memorious.back.controller;

import com.memorious.back.dto.ChartDataReqDto;
import com.memorious.back.dto.ChartDataUpdateReqDto;
import com.memorious.back.dto.ChartGraphDataReqDto;
import com.memorious.back.dto.ChartGraphDataRespDto;
import com.memorious.back.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChartController {
    private final ChartService chartService;

    @GetMapping("/chart/family")
    public ResponseEntity<?> getFamilyList(@RequestParam int familyId) {
        return ResponseEntity.ok(chartService.getFamilyList(familyId));
    }

    @PostMapping("/chart")
    public ResponseEntity<?> addChartData(@RequestBody ChartDataReqDto chartDataReqDto) {
        return ResponseEntity.ok(chartService.insertChartData(chartDataReqDto));
    }

    @GetMapping("/chart")
    public ResponseEntity<?> getChartTableData(@RequestParam String userId) {
        return ResponseEntity.ok(chartService.getChartDataForTable(userId));
    }

    @PutMapping("/chart")
    public ResponseEntity<?> editChartTableData(@RequestBody ChartDataUpdateReqDto chartDataUpdateReqDto) {
        return ResponseEntity.ok(chartService.updateChartData(chartDataUpdateReqDto));
    }

    @PostMapping("/chart/graph")
    public ResponseEntity<?> getChartGraphData(@RequestBody ChartGraphDataReqDto chartGraphDataReqDto) {
        return ResponseEntity.ok(chartService.getChartDataForChart(chartGraphDataReqDto));
    }
}
