package com.memorious.back.entity;

import com.memorious.back.dto.PrincipalRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int userId;
    private String email;
    private String nickname;
    private String oauth2Id;
    private String provider;
    private String profileUrl;
    private String role;

    public PrincipalRespDto toPrincipalDto() {
        return PrincipalRespDto.builder()
                .userId(userId)
                .email(email)
                .nickname(nickname)
                .oauth2Id(oauth2Id)
                .provider(provider)
                .profileUrl(profileUrl)
                .role(role)
                .build();
    }
}
