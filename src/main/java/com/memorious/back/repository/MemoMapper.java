package com.memorious.back.repository;

import com.memorious.back.entity.MemoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemoMapper {
    public int writeMemo(MemoEntity memoEntity);
}
