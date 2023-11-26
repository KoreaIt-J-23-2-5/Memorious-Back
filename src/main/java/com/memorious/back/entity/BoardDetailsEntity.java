package com.memorious.back.entity;

import com.memorious.back.dto.BoardDetailsRespDto;
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
public class BoardDetailsEntity {
    private int boardId;
    private int boardCategoryId;
    private String boardCategoryName;
    private String boardTitle;
    private String boardContent;
    private String profileUrl;
    private String nickname;
    private LocalDateTime createDate;

    public BoardDetailsRespDto toBoardDetailsDto() {
        return BoardDetailsRespDto.builder()
                .boardId(boardId)
                .boardCategoryId(boardCategoryId)
                .boardCategoryName(boardCategoryName)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .profileUrl(profileUrl)
                .nickname(nickname)
                .createDate(createDate.format(DateTimeFormatter.ISO_DATE))
                .build();
    }
}
