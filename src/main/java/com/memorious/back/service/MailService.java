package com.memorious.back.service;

import com.memorious.back.dto.InviteReqDto;
import com.memorious.back.jwt.JwtProvider;
import com.memorious.back.repository.InviteMapper;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {
	private final JavaMailSender javaMailSender;
	private final JwtProvider jwtProvider;
	private final InviteMapper inviteMapper;

	@Transactional(rollbackFor = Exception.class)
	public boolean sendInvitation(InviteReqDto inviteReqDto) {
		// todo : (1) 요청받은 이메일로 전송
//        String userId = ;
		System.out.println(SecurityContextHolder.getContext().getAuthentication());
	    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		System.out.println(inviteReqDto);
		String email = inviteReqDto.getEmail();

//        String inviteEmail = claims.get("email").toString();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			helper.setSubject("Memorious 가족에 초대합니다.");
			helper.setFrom("Seonggwangdev@gmail.com");
			helper.setTo(email);
//			String token = jwtProvider.generateToken();
			mimeMessage.setText(
           "<div>" +
                "<h1>사용자 인증 메일</h1>" +
                "<p>사용자 인증을 완료하려면 아래의 버튼을 클릭하세요.</p>" +
                "<a href=\"http://localhost:8080/auth/mail?token=\">인증하기</a>" +
				"<div> test !!!!! </div> " +
           "</div>", "utf-8", "html"
			);
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		// todo : (2) 이메일 전송 후 invitation_history_tb 에 insert

		return inviteMapper.addHistory(inviteReqDto.dtoToEntity()) > 0;
	}
}
