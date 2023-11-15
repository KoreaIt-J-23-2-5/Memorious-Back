package com.memorious.back.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class DuplicateException extends RuntimeException {
    private Map<String, String> errorMap;

    //ControllerAdvice에서 처리
    public DuplicateException(Map<String, String> errorMap) {
        super("중복 검사 오류");
        this.errorMap = errorMap;
    }

}
