package com.memorious.back.service;

import com.memorious.back.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailService {
	private final JavaMailSender javaMailSender;
	private final JwtProvider jwtProvider;

	public boolean sendAuthMail() {
//		todo : 요청받은 이메일로 전송해야함
//		String toEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		String toEmail = "jusg0721@naver.com";

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try{
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			helper.setSubject("Memorious 가족에 초대합니다.");
			helper.setFrom("Seonggwangdev@gmail.com");
			helper.setTo(toEmail);
//			String token = jwtProvider.generateToken(toEmail);
			mimeMessage.setText(
           "<div>" +
               "<h1>사용자 인증 메일</h1>" +
               "<p>사용자 인증을 완료하려면 아래의 버튼을 클릭하세요.</p>" +
//               "<a href=\"http://localhost:8080/auth/mail?token=" + token + "\">인증하기</a>" +
           "</div>", "utf-8", "html"
			);
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			return false;
		}
		return true;
	}


}
