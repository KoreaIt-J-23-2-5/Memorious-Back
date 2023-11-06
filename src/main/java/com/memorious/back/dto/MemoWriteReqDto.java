package com.memorious.back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemoWriteReqDto {
    private String author;
    private String memoContent;
    private String createdDate;
}
