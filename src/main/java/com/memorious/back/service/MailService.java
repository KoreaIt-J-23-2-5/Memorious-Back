package com.memorious.back.service;

import com.memorious.back.dto.TestDto;
import com.memorious.back.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailService {
	private final JavaMailSender javaMailSender;
	private final JwtProvider jwtProvider;

	@Transactional(rollbackFor = Exception.class)
	public boolean sendInvitation(String email) {
		// todo : (1) 요청받은 이메일로 전송
//		Claims claims = jwtProvider.getClaims(token);
//      String toEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//		String toEmail = "jusg0721@naver.com";

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		System.out.println("email : " + email);

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
		return true;
		// todo : (2) 이메일 전송 후 invitation_history_tb 에 insert
	}
}
