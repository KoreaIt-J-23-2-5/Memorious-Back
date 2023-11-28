package com.memorious.back.repository;

import com.memorious.back.entity.FamilyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface FamilyMapper {
    public int saveFamily(FamilyEntity familyEntity);
    public int saveMember(Map<String, Integer> map);
}
