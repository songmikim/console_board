package org.koreait.member.services;

import org.koreait.global.configs.DBConn;
import org.koreait.global.validators.Validator;
import org.koreait.member.controllers.RequestJoin;
import org.koreait.member.entities.Member;
import org.koreait.member.mappers.MemberMapper;
import org.mindrot.jbcrypt.BCrypt;

public class MemberJoinService {
    private final Validator<RequestJoin> validator;
    private MemberMapper mapper;

    public MemberJoinService(Validator<RequestJoin> validator, MemberMapper mapper) {
        this.validator = validator;
        this.mapper = mapper;
    }

    public void process(RequestJoin form) {
        // 회원 가입을 위한 데이터의 검증
        validator.check(form);

        String hash = BCrypt.hashpw(form.getPassword(), BCrypt.gensalt(12));

        String mobile = form.getMobile();
        // 휴대전화 번호는 숫자외 문자는 모두 제거
        if (mobile != null && !mobile.isBlank()) {
            mobile = mobile.replaceAll("\\D", ""); //
        }

        Member member = new Member();
        member.setEmail(form.getEmail());
        member.setPassword(hash);
        member.setName(form.getName());
        member.setMobile(mobile);
        member.setTerms(form.isTerms());

        mapper.register(member);


        mapper = DBConn.getInstance().getSession().getMapper(MemberMapper.class);
    }
}
