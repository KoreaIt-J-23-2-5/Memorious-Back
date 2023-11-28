package com.memorious.back.entity;

import com.memorious.back.dto.FamilyRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyEntity {
    private int familyId;
    private String familyName;
    private String profileUrl;
    private int ownerId;

    public FamilyRespDto toDto() {
        return FamilyRespDto.builder()
                .familyId(familyId)
                .familyName(familyName)
                .profileUrl(profileUrl)
                .ownerId(ownerId)
                .build();
    }
}
