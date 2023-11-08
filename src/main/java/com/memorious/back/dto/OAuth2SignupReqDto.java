package com.memorious.back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OAuth2SignupReqDto {
    private String email;
    private String nickname;
    private String oauth2Id;
    private String provider;
}
