package com.memorious.back.dto;

import com.memorious.back.entity.BoardEntity;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Builder
@Data
public class BoardEditReqDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @Min(0)
    private int categoryId;
    @NotBlank
    private String categoryName;

    public BoardEntity toBoardEntity(String nickname) {
        return BoardEntity.builder()
                .boardTitle(title)
                .boardCategoryId(categoryId)
                .boardContent(content)
                .nickname(nickname)
                .build();
    }
}
