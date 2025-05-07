package org.koreait.global.exceptions;

public class BadRequestException extends CommonException {

    public BadRequestException() {
        this("잘못된 요청입니다.");
    }

    public BadRequestException(String message) {
        super(message, 400);
    }
}
