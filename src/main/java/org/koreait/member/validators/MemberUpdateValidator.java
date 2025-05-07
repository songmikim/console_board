package org.koreait.member.validators;

import org.koreait.global.validators.MobileValidator;
import org.koreait.global.validators.RequiredFieldValidator;
import org.koreait.global.validators.Validator;
import org.koreait.member.controllers.RequestJoin;

public class MemberUpdateValidator implements Validator<RequestJoin>, RequiredFieldValidator, MobileValidator {
    @Override
    public void check(RequestJoin form) {
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();
        String mobile = form.getMobile();

        if (password != null && !password.isBlank()) {
            checkTrue(password.length() >= 8, "비밀번호는 8자리 이상 입력하세요.");
            checkString(confirmPassword, "비밀번호를 확인하세요.");
            checkTrue(password.equals(confirmPassword), "입력하신 비밀번호 확인이 일치하지 않습니다.");
        }

        if (mobile != null && !mobile.isBlank()) {
            checkTrue(checkMobile(mobile), "휴대전화번호 형식이 아닙니다.");
        }
    }
}
