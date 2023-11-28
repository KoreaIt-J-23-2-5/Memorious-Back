package com.memorious.back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FamilyListRespDto {
    int familyId;
    int userId;
    String familyName;
    String nickname;
    String imgSrc;
    boolean checked;
    int ownerId;
}


