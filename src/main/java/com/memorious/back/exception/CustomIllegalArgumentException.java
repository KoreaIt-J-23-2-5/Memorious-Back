package com.memorious.back.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomIllegalArgumentException extends Exception {
    private Map<String, String> errorMap;

    //ControllerAdvice에서 처리
    public CustomIllegalArgumentException(Map<String, String> errorMap) {
        super("토큰이 존재하지 않음");
        this.errorMap = errorMap;
    }
}
