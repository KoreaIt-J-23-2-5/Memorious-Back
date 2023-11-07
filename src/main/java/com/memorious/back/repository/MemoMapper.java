package com.memorious.back.repository;

import com.memorious.back.entity.MemoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoMapper {
    public int writeMemo(MemoEntity memoEntity);
    public List<MemoEntity> getMemoList(int index);
    public int getMemoListTotalCount();
    public int updateMemoContent(MemoEntity memoEntity);
    public int deleteMemoContent(int index);
    public List<MemoEntity> findMemo(String searchkey);
}
