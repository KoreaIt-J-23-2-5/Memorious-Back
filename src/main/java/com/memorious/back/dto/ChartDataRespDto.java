package com.memorious.back.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ChartDataRespDto {
    private int chartDataId;
    private LocalDate date;
    private int step;
    private int pulse;
    private int fbs;
    private String buttons;
}
