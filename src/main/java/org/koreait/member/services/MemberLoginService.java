package org.koreait.member.services;

import org.koreait.global.validators.Validator;
import org.koreait.member.MemberSession;
import org.koreait.member.controllers.RequestLogin;
import org.koreait.member.entities.Member;

public class MemberLoginService {
    private final Validator<RequestLogin> validator;
    private final MemberInfoService infoService;

    public MemberLoginService(Validator<RequestLogin> validator, MemberInfoService infoService) {
        this.validator = validator;
        this.infoService = infoService;
    }

    public void process(RequestLogin form) {
        // 로그인 요청 데이터의 검사(유효성 검사)
        validator.check(form);

        // 로그인 처리
        /**
         * 애플리케이션 전역에 인증을 받은 회원의 정보를 유지
         *
         */
        Member member = infoService.get(form.getEmail());
        MemberSession.setMember(member);
    }
}
