package org.koreait.member.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.koreait.global.configs.DBConn;
import org.koreait.member.MemberSession;
import org.koreait.member.controllers.RequestLogin;
import org.koreait.member.mappers.MemberMapper;
import org.koreait.member.validators.MemberLoginValidator;

public class MemberLoginServiceTest {

    private MemberLoginService service;

    @BeforeEach
    void init() {
        MemberMapper mapper = DBConn.getInstance().getSession().getMapper(MemberMapper.class);
        MemberInfoService infoService = new MemberInfoService(mapper);
        MemberLoginValidator validator = new MemberLoginValidator(infoService);
        service = new MemberLoginService(validator, infoService);
    }

    @Test
    void test1() {
        RequestLogin form = new RequestLogin();
        form.setEmail("user01@test.org");
        form.setPassword("12345678");

        service.process(form);

        System.out.printf("로그인 여부:%s, 회원정보:%s%n", MemberSession.isLogin(), MemberSession.getMember());
    }
}
