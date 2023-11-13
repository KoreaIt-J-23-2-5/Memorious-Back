package com.memorious.back.controller;

import com.memorious.back.exception.JoinMailException;
import com.memorious.back.exception.DuplicateException;
import com.memorious.back.exception.ValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //예외를 처리하기 위한 컨트롤러
public class ExceptionControllerAdvice {

    @ExceptionHandler(ValidException.class)
    public ResponseEntity<?> validException(ValidException validException){
        System.out.println("validException 예외처리");
        return ResponseEntity.badRequest().body(validException.getErrorMap());
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<?> duplicateException(DuplicateException duplicateException){
        System.out.println("duplicateException 예외처리");
        return ResponseEntity.badRequest().body(duplicateException.getErrorMap());
    }

    @ExceptionHandler(JoinMailException.class)
   	public ResponseEntity<?> mailException(JoinMailException mailException) {
   		Map<String, String> message = new HashMap<>();
   		message.put("authMail", mailException.getMessage());
   		return ResponseEntity.ok().body(message);
   	}
}
