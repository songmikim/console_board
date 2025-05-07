package org.koreait.member.services;

import org.koreait.member.MemberSession;
import org.koreait.member.entities.Member;
import org.koreait.member.exceptions.MemberNotFoundException;
import org.koreait.member.mappers.MemberMapper;

public class MemberDeleteService {
    private final MemberMapper mapper;

    public MemberDeleteService(MemberMapper mapper) {
        this.mapper = mapper;
    }

    public void deleteCurrentMember() {
        Member member = MemberSession.getMember();
        if (member == null || member.getEmail() == null) {
            throw new MemberNotFoundException("로그인된 회원이 없습니다.");
        }

        mapper.delete(member.getEmail());

        // 세션 초기화 (로그아웃 처리)
        MemberSession.setMember(null);
    }
}