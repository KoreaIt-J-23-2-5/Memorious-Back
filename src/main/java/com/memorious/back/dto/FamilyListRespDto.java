package com.memorious.back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FamilyListRespDto {
    int userId;
    String nickname;
    String imgSrc;
}
