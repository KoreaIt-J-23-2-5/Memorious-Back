package com.memorious.back.controller;

import com.memorious.back.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Repeatable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class InviteController {

	private final MailService mailService;

// mail 보내는 요청에 대한 응답
	@PostMapping("/invitation/mail")
	public ResponseEntity<?> sendInviteMail() {
		return mailService.sendAuthMail().ok("true");
	}

	//family에 insert. 회원가입 후 진행해야함
	@PostMapping("/invitation/join")
	public ResponseEntity<?> joinFamily(String email) {
		return ResponseEntity.ok(null);
	}
}
