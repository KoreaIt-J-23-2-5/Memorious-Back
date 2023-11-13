package com.memorious.back.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ChartGraphDataRespDto {
    String username;
    Map<String, List<Integer>> chartData;
}
