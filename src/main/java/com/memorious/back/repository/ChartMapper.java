package com.memorious.back.repository;

import com.memorious.back.dto.ChartDataRespDto;
import com.memorious.back.entity.ChartDataEntity;
import com.memorious.back.entity.ChartGraphEntity;
import com.memorious.back.entity.FamilyListEntity;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface ChartMapper {
    public int addChartData(ChartDataEntity chartDataEntity);
    public List<ChartDataEntity> getChartDataForTable(String userId);
    public int updateChartTableRow(ChartDataEntity chartDataEntity);
    public List<Integer> getFbsDataForChart(Map<String, Object> infoMap);
    public List<Integer> getStepDataForChart(Map<String, Object> infoMap);
    public List<Integer> getPulseDataForChart(Map<String, Object> infoMap);
    public List<FamilyListEntity> getFamilyList(int userId);
    public String getUsername(int userId);
}
