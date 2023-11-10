package com.memorious.back.dto;

import com.memorious.back.entity.ChartDataEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChartDataUpdateReqDto {
    private int chartDataId;
    private int step;
    private int fbs;
    private int pulse;

    public ChartDataEntity toEntity () {
        return ChartDataEntity.builder()
                .chartDataId(chartDataId)
                .step(step)
                .fbs(fbs)
                .pulse(pulse)
                .build();
    }
}
