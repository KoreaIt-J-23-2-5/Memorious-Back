package com.memorious.back.repository;

import com.memorious.back.dto.ChartDataRespDto;
import com.memorious.back.entity.ChartDataEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChartMapper {
    public int addChartData(ChartDataEntity chartDataEntity);
    public List<ChartDataEntity> getChartDataForTable(String userId);
    public int updateChartTableRow(ChartDataEntity chartDataEntity);
}
