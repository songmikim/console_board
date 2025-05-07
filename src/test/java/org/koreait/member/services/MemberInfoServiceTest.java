package org.koreait.member.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.koreait.global.configs.DBConn;
import org.koreait.global.paging.SearchForm;
import org.koreait.member.entities.Member;
import org.koreait.member.mappers.MemberMapper;

import java.util.List;

public class MemberInfoServiceTest {

    private MemberInfoService service;

    @BeforeEach
    void init() {
        MemberMapper mapper = DBConn.getInstance().getSession().getMapper(MemberMapper.class);
        service = new MemberInfoService(mapper);
    }

    @Test
    void test1() {
//        Member member = service.get("user01@test.org");
//        System.out.println(member);

        SearchForm search = new SearchForm();
        List<Member> items = service.getList(search);
        search.setSopt("email");
        search.setSkey("user");
        items.forEach(System.out::println);
    }
}
