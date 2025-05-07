package org.koreait.member.services;

import org.koreait.global.configs.DBConn;
import org.koreait.global.validators.Validator;
import org.koreait.member.MemberSession;
import org.koreait.member.controllers.RequestJoin;
import org.koreait.member.entities.Member;
import org.koreait.member.mappers.MemberMapper;
import org.mindrot.jbcrypt.BCrypt;

public class MemberUpdateService {
    private MemberMapper mapper;
    private final Validator<RequestJoin> validator;

    public MemberUpdateService(MemberMapper mapper, Validator<RequestJoin> validator) {
        this.mapper = mapper;
        this.validator = validator;
    }

    public void process(RequestJoin form) {
        // 회원정보 수정 데이터의 검증(유효성 검사)
        validator.check(form);

        Member member = new Member();
        member.setEmail(MemberSession.getMember().getEmail());
        member.setName(form.getName());

        // 비밀번호 변경인 경우 BCrypt 해시로 변환
        String password = form.getPassword();
        if (password != null && !password.isBlank()) {
            member.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
        }

        // 휴대전화번는 숫자만 남긴다.
        String mobile = form.getMobile();
        if (mobile != null && !mobile.isBlank()) {
            mobile = mobile.replaceAll("\\D", "");
            member.setMobile(mobile);
        }

        mapper.update(member);

        mapper = DBConn.getInstance().getSession().getMapper(MemberMapper.class);

        // 로그인 회원 정보도 갱신
        member = mapper.get(member.getEmail()).orElse(null);
        MemberSession.setMember(member);
    }
}
