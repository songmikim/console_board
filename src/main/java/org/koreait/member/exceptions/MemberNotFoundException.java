package org.koreait.member.exceptions;

import org.koreait.global.exceptions.NotFoundException;

public class MemberNotFoundException extends NotFoundException {
    public MemberNotFoundException(String s) {
        super("회원을 찾을 수 없습니다.");
    }
}
