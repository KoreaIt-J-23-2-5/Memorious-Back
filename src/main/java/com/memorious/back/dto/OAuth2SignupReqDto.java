package com.memorious.back.dto;

import com.memorious.back.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OAuth2SignupReqDto {
    private String email;
    private String nickname;
    private String oauth2Id;
    private String provider;

    public User toUserEntity(){
        return User.builder()
                .email(email)
                .nickname(nickname)
                .oauth2Id(oauth2Id)
                .provider(provider)
                .build();
    }
}
