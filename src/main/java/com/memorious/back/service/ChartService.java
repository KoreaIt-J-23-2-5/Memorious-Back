package com.memorious.back.service;

import com.memorious.back.dto.*;
import com.memorious.back.entity.ChartDataEntity;
import com.memorious.back.repository.ChartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChartService {
    private final ChartMapper chartMapper;

    public List<FamilyListRespDto> getFamilyList(int familyId) {
        List<FamilyListRespDto> familyList = new ArrayList<>();
        chartMapper.getFamilyList(familyId).forEach(member -> {
            familyList.add(member.toDto());
        });
        return familyList;
    }
    @Transactional(rollbackFor = Exception.class)
    public boolean insertChartData(ChartDataReqDto chartDataReqDto) {
        ChartDataEntity chartDataEntity = chartDataReqDto.toEntity();
        return chartMapper.addChartData(chartDataEntity) > 0;
    }

    public List<ChartDataRespDto> getChartDataForTable(String userId) {
        List<ChartDataRespDto> dtoList = new ArrayList<>();
        chartMapper.getChartDataForTable(userId).forEach(data -> {
            dtoList.add(data.toDto());
        });
        System.out.println(dtoList);
        return dtoList;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateChartData(ChartDataUpdateReqDto chartDataUpdateReqDto) {
        return chartMapper.updateChartTableRow(chartDataUpdateReqDto.toEntity()) > 0;
    }

    public Map<String, Map<String, List<Integer>>> getChartDataForChart(ChartGraphDataReqDto chartGraphDataReqDto) {
        if(chartGraphDataReqDto.getUserList().size() > 0){
            Map<String, Map<String, List<Integer>>> resultMap = new HashMap<>();
            for (int userId : chartGraphDataReqDto.getUserList()) {
                String nickname = chartMapper.getUsername(userId);
                LocalDate startDate = chartGraphDataReqDto.getStartDate();
                Map<String, Object> infoMap = new HashMap<>();

                infoMap.put("startDate", startDate.toString());
                infoMap.put("userId", userId);
                System.out.println(infoMap);
                Map<String, List<Integer>> queryMap = new HashMap<>();
                queryMap.put("step", chartMapper.getStepDataForChart(infoMap));
                queryMap.put("fbs", chartMapper.getFbsDataForChart(infoMap));
                queryMap.put("pulse", chartMapper.getPulseDataForChart(infoMap));
                resultMap.put(nickname, queryMap);
            }
            return resultMap;
        }
        return null;
    }

}
