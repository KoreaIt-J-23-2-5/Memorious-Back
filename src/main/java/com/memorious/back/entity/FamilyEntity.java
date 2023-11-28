package com.memorious.back.entity;

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
}
