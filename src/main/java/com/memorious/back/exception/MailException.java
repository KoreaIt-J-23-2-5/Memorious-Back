package com.memorious.back.exception;

import javax.mail.Message;

public class MailException extends RuntimeException {

	public MailException(String message) {
	super(message);
		}
}
