package com.memorious.back.service;

import com.memorious.back.dto.ChartDataReqDto;
import com.memorious.back.dto.ChartDataRespDto;
import com.memorious.back.dto.ChartDataUpdateReqDto;
import com.memorious.back.entity.ChartDataEntity;
import com.memorious.back.repository.ChartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartService {
    private final ChartMapper chartMapper;

    public boolean insertChartData(ChartDataReqDto chartDataReqDto) {
        ChartDataEntity chartDataEntity = chartDataReqDto.toEntity();
        return chartMapper.addChartData(chartDataEntity) > 0;
    }

    public List<ChartDataRespDto> getChartDataForTable(String userId) {
        List<ChartDataRespDto> dtoList = new ArrayList<>();
        chartMapper.getChartDataForTable(userId).forEach(data -> {
            dtoList.add(data.toDto());
        });
        return dtoList;
    }

    public boolean updateChartData(ChartDataUpdateReqDto chartDataUpdateReqDto) {
        return chartMapper.updateChartTableRow(chartDataUpdateReqDto.toEntity()) > 0;
    }

}
