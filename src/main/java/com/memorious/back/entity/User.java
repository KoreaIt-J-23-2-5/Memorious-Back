package com.memorious.back.entity;

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
    private int enabled;
    private String oauth2Id;
    private String provider;
    private String profileUrl;
}
