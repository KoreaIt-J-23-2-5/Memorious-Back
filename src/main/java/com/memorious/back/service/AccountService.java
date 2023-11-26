package com.memorious.back.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.memorious.back.dto.UpdateProfileImgReqDto;
import com.memorious.back.entity.User;
import com.memorious.back.jwt.JwtProvider;
import com.memorious.back.repository.UserMapper;
import com.memorious.back.security.PrincipalUser;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.StyledEditorKit;

@Service
@RequiredArgsConstructor
public class AccountService {

	private final JwtProvider jwtProvider;
	private final UserMapper userMapper;

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

	@Transactional(rollbackFor = Exception.class)
	public boolean updateProfileImg(UpdateProfileImgReqDto updateProfileImgReqDto) {
		System.out.println(updateProfileImgReqDto);
		PrincipalUser principalUser =(PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = principalUser.getUser().getEmail();
		System.out.println(email);

		return userMapper.updateProfileUrl(User.builder()
				.email(email)
				.profileUrl(updateProfileImgReqDto.getProfileUrl())
				.build()) > 0;
	}

}
