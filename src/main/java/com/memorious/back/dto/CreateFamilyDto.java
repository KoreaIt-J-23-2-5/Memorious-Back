package com.memorious.back.dto;

import com.memorious.back.entity.FamilyEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateFamilyDto {
    @NotBlank
    private String familyName;

    public FamilyEntity toFamilyEntity(int userId) {
        return FamilyEntity.builder()
                .familyName(familyName)
                .ownerId(userId)
                .build();
    }
}
