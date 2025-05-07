package org.koreait.board.controllers;

import org.koreait.member.MemberSession;
import org.koreait.member.entities.Member;

public class BoardListController implements BoardList {

    @Override
    public void show() {
        Member member = MemberSession.getMember();
        StringBuffer sb = new StringBuffer(5000);
        sb.append(String.format("1. 이메일: %s%n", member.getEmail()))
                .append(String.format("2. 회원명: %s%n", member.getName()))
                .append(String.format("3. 휴대전화번호: %s", member.getMobile()));
        System.out.println(sb);
    }

    @Override
    public void common() {
        System.out.println("******************* 게시글 목록 ********************");
    }
}

