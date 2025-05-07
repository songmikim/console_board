package org.koreait.member.validators;

import org.koreait.global.validators.RequiredFieldValidator;
import org.koreait.global.validators.Validator;
import org.koreait.member.controllers.RequestLogin;
import org.koreait.member.entities.Member;
import org.koreait.member.exceptions.MemberNotFoundException;
import org.koreait.member.services.MemberInfoService;
import org.mindrot.jbcrypt.BCrypt;

public class MemberLoginValidator implements Validator<RequestLogin>, RequiredFieldValidator {
    private final MemberInfoService service;

    public MemberLoginValidator(MemberInfoService serivce) {
        this.service = serivce;
    }


    /**
     * 1. 필수항목 검증 - 이메일, 비밀번호
     * 2. 이메일로 회원이 조회 되는지
     * 3. 비밀번호가 일치하는지 체크
     *
     * @param form
     */
    @Override
    public void check(RequestLogin form) {
        String email = form.getEmail();
        String password = form.getPassword();

        // 1. 필수항목 검증 - 이메일, 비밀번호
        checkString(email, "이메일을 입력하세요.");
        checkString(password, "비밀번호를 입력하세요.");


        String message = "이메일 또는 비밀번호가 일치하지 않습니다.";

        // 2. 이메일로 회원이 조회 되는지
        Member member = null;
        try {
            member = service.get(email);
        } catch (MemberNotFoundException e) {}
        checkTrue(member != null, message);

        // 3. 비밀번호가 일치하는지 체크
        checkTrue(BCrypt.checkpw(form.getPassword(), member.getPassword()), message);
    }
}
