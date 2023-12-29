package com.memorious.back.service;

import com.memorious.back.dto.InviteReqDto;
import com.memorious.back.entity.User;
import com.memorious.back.jwt.JwtProvider;
import com.memorious.back.repository.InviteMapper;
import com.memorious.back.security.PrincipalUser;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class MailService {
	private final JavaMailSender javaMailSender;
	private final JwtProvider jwtProvider;
	private final InviteMapper inviteMapper;


	@Transactional(rollbackFor = Exception.class)
	public boolean sendInvitation(Map<String, String> invitedEmailMap) {
		String invitedEmail = invitedEmailMap.get("email");

		PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = principalUser.getUser();

		int userId = user.getUserId();
		int familyId = user.getFamilyId();
		String username = user.getNickname();

		Map<String, Integer> getFamilyNameMap = new HashMap<>();
		getFamilyNameMap.put("userId", userId);
		getFamilyNameMap.put("familyId", familyId);
		String familyName = inviteMapper.getFamilyName(getFamilyNameMap);
	    MimeMessage mimeMessage = javaMailSender.createMimeMessage();


		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

			helper.setSubject("Memorious 가족에 초대합니다.");
			helper.setFrom("noreply@Memorious.co.kr");
			helper.setTo(invitedEmail);
			String token = jwtProvider.generateAuthMailToken(familyId);
			mimeMessage.setText(
           "<div>" +
                "<h1> Memorious에 초대합니다.\n</h1>" +
                "<h2>" + username + "님이 '" + familyName + "' 공간에 초대했습니다.\n</h2>" +
                "<p>초대를 받으시려면 소셜 로그인 후, 본 이메일과 같은 이메일 주소를 입력해 회원가입을 완료해주세요.\n</p>" +
			   "<a href=\"http://localhost:3000/invitation/auth/token?=" + token + "\">초대 수락하기</a>" +
           "</div>", "utf-8", "html"
			);
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		// todo : (2) 이메일 전송 후 invitation_history_tb 에 insert

		Map<String, Object> addHistoryMap = new HashMap<>();
		addHistoryMap.put("userId", userId);
		addHistoryMap.put("invitedEmail", invitedEmail);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String expirationDateTime  = now.plusHours(72).format(formatter);
		addHistoryMap.put("expirationDateTime", expirationDateTime);

		return inviteMapper.addHistory(addHistoryMap) > 0;
	}
}
