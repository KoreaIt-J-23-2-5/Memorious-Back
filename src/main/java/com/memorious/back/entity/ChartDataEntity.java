package com.memorious.back.entity;

import com.memorious.back.dto.ChartDataReqDto;
import com.memorious.back.dto.ChartDataRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChartDataEntity {
    private int chartDataId;
    private LocalDate date;
    private String userId;
    private int step;
    private int pulse;
    private int fbs;
    private String buttons;

    public ChartDataRespDto toDto() {
        return ChartDataRespDto.builder()
                .chartDataId(chartDataId)
                .date(date)
                .step(step)
                .pulse(pulse)
                .fbs(fbs)
                .buttons("")
                .build();
    }
}
