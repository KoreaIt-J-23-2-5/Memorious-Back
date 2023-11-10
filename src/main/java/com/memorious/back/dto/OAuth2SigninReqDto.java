package com.memorious.back.dto;

import com.memorious.back.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OAuth2SigninReqDto {
    String oauth2Id;
    String provider;
    String nickname;
    String email;

    public User toEntity () {
        return User.builder()
                .email(email)
                .provider(provider)
                .oauth2Id(oauth2Id)
                .nickname(nickname)
                .build();
    }
}
