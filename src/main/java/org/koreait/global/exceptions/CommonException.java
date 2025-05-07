package org.koreait.global.exceptions;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {
    private final int status;

    public CommonException(String message) {
        this(message, 500);
    }

    public CommonException(String message, int status) {
        super(message);
        this.status = status;
    }
}
