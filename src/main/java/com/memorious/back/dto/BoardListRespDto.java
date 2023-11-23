package com.memorious.back.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BoardListRespDto {
    private int boardId;
    private String title;
    private String nickname;
    private String createDate;
}
