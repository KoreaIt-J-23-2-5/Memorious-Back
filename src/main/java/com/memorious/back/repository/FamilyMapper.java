package com.memorious.back.repository;

import com.memorious.back.entity.FamilyEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FamilyMapper {
    public int saveFamily(FamilyEntity familyEntity);
}
