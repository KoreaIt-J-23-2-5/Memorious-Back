package com.memorious.back.repository;

import com.memorious.back.entity.BoardCategoryEntity;
import com.memorious.back.entity.BoardEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<BoardCategoryEntity> getBoardCategories();
    public int saveBoardCategory(BoardCategoryEntity boardCategory);
    public int saveBoardContent(BoardEntity boardEntity);
}
