package com.memorious.back.entity;

import com.memorious.back.dto.BoardListRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public BoardListRespDto toBoardListDto(){
        return BoardListRespDto.builder()
            .boardId(boardId)
            .title(boardTitle)
            .nickname(nickname)
            .createDate(createDate.format(DateTimeFormatter.ISO_DATE))
            .build();
    }
}
