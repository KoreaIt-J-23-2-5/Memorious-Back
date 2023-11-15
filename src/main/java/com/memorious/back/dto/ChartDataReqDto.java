package com.memorious.back.dto;

import com.memorious.back.entity.ChartDataEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ChartDataReqDto {
    private LocalDate date;
    private int step;
    private int pulse;
    private int fbs;
    private String userId;

    public ChartDataEntity toEntity () {
        return ChartDataEntity.builder().date(date)
                    .step(step)
                    .pulse(pulse)
                    .fbs(fbs)
                    .userId(userId)
                    .build();
    }
}
