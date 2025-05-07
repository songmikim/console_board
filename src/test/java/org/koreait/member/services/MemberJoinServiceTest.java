package org.koreait.member.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.koreait.global.configs.DBConn;
import org.koreait.member.controllers.RequestJoin;
import org.koreait.member.mappers.MemberMapper;
import org.koreait.member.validators.MemberJoinValidator;

public class MemberJoinServiceTest {

    private MemberJoinService service;

    @BeforeEach
    void init() {
        MemberMapper mapper = DBConn.getInstance().getSession().getMapper(MemberMapper.class);
        MemberJoinValidator validator = new MemberJoinValidator(mapper);
        service = new MemberJoinService(validator, mapper);
    }

    @Test
    void test1() {
        RequestJoin form = new RequestJoin();
        form.setEmail("user02@test.org");
        form.setName("사용자01");
        form.setPassword("12345678");
        form.setConfirmPassword(form.getPassword());
        form.setMobile("00001034812101");
        form.setTerms(true);

        service.process(form);
    }
}
