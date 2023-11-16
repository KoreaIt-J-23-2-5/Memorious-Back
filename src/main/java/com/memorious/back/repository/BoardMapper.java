package com.memorious.back.repository;

import com.memorious.back.entity.BoardCategoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<BoardCategoryEntity> getBoardCategories();
}
