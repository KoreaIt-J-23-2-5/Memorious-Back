package com.memorious.back.repository;

import com.memorious.back.entity.BoardCategoryEntity;
import com.memorious.back.entity.BoardDetailsEntity;
import com.memorious.back.entity.BoardEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    public List<BoardCategoryEntity> getBoardCategories();
    public int saveBoardCategory(BoardCategoryEntity boardCategory);
    public int saveBoardContent(BoardEntity boardEntity);
    public List<BoardEntity> getBoardList(Map<String, Object> paramsMap);
    public BoardDetailsEntity getBoardByBoardId(int boardId);
    public int updateBoard(BoardEntity boardEntity);
    public int deleteBoard(int boardId);
}
