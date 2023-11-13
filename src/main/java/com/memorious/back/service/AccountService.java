package com.memorious.back.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.memorious.back.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {
	//    private final UserMapper userMapper;
	private final JwtProvider jwtProvider;

	@Transactional(rollbackFor = Exception.class)
	public boolean authenticateMail(String token) {
		return true;
//        Claims claims = jwtProvider.getClaims(token);
//        if(claims == null) {
//            throw new AuthMailException("만료된 인증 요청입니다.");
//        }
//        String email = claims.get("email").toString();
//        System.out.println(email);
//        User user = userMapper.findUserByEmail(email);
//        if(user.getEnabled() > 0) {
//            throw new AuthMailException("이미 인증이 완료된 요청입니다.");
//        }
//        return userMapper.updateEnabledToEmail(email) > 0;
	}
}
