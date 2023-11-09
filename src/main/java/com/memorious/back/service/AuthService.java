package com.memorious.back.service;

import com.memorious.back.dto.OAuth2SignupReqDto;
import com.memorious.back.entity.User;
import com.memorious.back.exception.DuplicateException;
import com.memorious.back.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserMapper userMapper;

    public boolean signupOAuth2(OAuth2SignupReqDto oAuth2SignupReqDto) {
//        userMapper.findUserByOAuth2Id(oAuth2SignupReqDto.getOauth2Id());

        // 중복체크 있어야 됨 (같은 계정 정보로 여러번 insert 되는 거 방지)
        User user = oAuth2SignupReqDto.toUserEntity();
        int errorCode = userMapper.checkDuplicate(user);
        if(errorCode > 0) {
            responseDuplicateError(errorCode);
        }

        return userMapper.saveUser(user) > 0;
    }

    private void responseDuplicateError(int errorCode) {
        Map<String, String> errorMap = new HashMap<>();
        switch (errorCode){
            case 1:
                //이메일 중복
                errorMap.put("email", "이미 사용중인 이메일입니다.");
                break;
            case 2:
                //닉네임 중복
                errorMap.put("nickname", "이미 사용중인 닉네임입니다.");
                break;
            case 3:
                //둘다 중복 -> 이메일 우선 처리
                errorMap.put("nickname", "이미 사용중인 닉네임입니다.");
                errorMap.put("email", "이미 사용중인 이메일입니다.");
                break;
        }

        throw new DuplicateException(errorMap);
    }
}
