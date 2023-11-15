package com.memorious.back.entity;

import com.memorious.back.dto.FamilyListRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FamilyListEntity {
    int userId;
    String nickname;
    String profileUrl;

    public FamilyListRespDto toDto() {
        return FamilyListRespDto.builder()
                .userId(userId)
                .nickname(nickname)
                .imgSrc(profileUrl)
                .build();
    }
}
