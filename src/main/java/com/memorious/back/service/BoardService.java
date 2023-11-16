package com.memorious.back.service;

import com.memorious.back.dto.BoardCategoryRespDto;
import com.memorious.back.entity.BoardCategoryEntity;
import com.memorious.back.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    public List<BoardCategoryRespDto> getBoardCategoriesAll() {
        List<BoardCategoryRespDto> boardCategoryRespDtos = new ArrayList<>();

        boardMapper.getBoardCategories().forEach(category -> {
            boardCategoryRespDtos.add(category.toCategoryRespDto());
        });

        return boardCategoryRespDtos;
    }
}
