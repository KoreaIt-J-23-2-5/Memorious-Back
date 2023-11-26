package com.memorious.back.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BoardDetailsRespDto {
    private int boardId;
    private int boardCategoryId;
    private String boardCategoryName;
    private String boardTitle;
    private String boardContent;
    private String profileUrl;
    private String nickname;
    private String createDate;
}
