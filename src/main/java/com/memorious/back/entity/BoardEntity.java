package com.memorious.back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardEntity {
    private int boardId;
    private String boardTitle;
    private int boardCategoryId;
    private String boardContent;
    private String nickname;
    private LocalDateTime createDate;
}
