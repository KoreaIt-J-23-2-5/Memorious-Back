package com.memorious.back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrincipalRespDto {
    private int userId;
    private String email;
    private String nickname;
    private String oauth2Id;
    private String provider;
    private String profileUrl;
    private String role;
    private int familyId;
}
