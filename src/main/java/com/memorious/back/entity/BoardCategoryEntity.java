package com.memorious.back.entity;

import com.memorious.back.dto.BoardCategoryRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardCategoryEntity {
    private int boardCategoryId;
    private String boardCategoryName;

    public BoardCategoryRespDto toCategoryRespDto() {
           return BoardCategoryRespDto.builder()
                   .boardCategoryId(boardCategoryId)
                   .boardCategoryName(boardCategoryName)
                   .build();
       }
}
