package com.memorious.back.entity;

import com.memorious.back.dto.MemoDto;
import com.memorious.back.dto.MemoListRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemoEntity {
    private int memoId;
    private String memoAuthorId;
    private String content;
    private String createdDate;

    public MemoListRespDto toDto() {
        return MemoListRespDto.builder()
                .memoId(memoId)
                .author(memoAuthorId)
                .memoContent(content)
                .createdDate(createdDate)
                .build();
    }
}
