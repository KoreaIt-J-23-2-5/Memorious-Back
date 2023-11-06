package com.memorious.back.dto;

import com.memorious.back.entity.MemoEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemoDto {
    private String author;
    private String memoContent;
    private String createdDate;

    public MemoEntity toEntity () {
        return MemoEntity.builder()
                .memoAuthorId(author)
                .content(memoContent)
                .createdDate(createdDate)
                .build();
    }
}
