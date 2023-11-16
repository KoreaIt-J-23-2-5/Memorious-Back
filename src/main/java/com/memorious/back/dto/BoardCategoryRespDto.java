package com.memorious.back.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BoardCategoryRespDto {
    private int boardCategoryId;
    private String boardCategoryName;
}