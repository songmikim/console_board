package org.koreait.member.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.koreait.global.configs.DBConn;

public class MapperTest {
    private MemberMapper mapper;

    @BeforeEach
    void init() {
        mapper = DBConn.getInstance().getSession().getMapper(MemberMapper.class);
    }

    @Test
    void test1() {
//        Member member = new Member();
//        member.setEmail("user01@test.org");
//        member.setPassword("12345678");
//        member.setName("사용자01");
//        member.setTerms(true);
//        member.setMobile("01034812101");
//
//        int affectedRows = mapper.register(member);
//        System.out.println(affectedRows);

//        Member member = mapper.get("user01@test.org").orElse(null);
//        System.out.println(member);

//        Member member = new Member();
//        member.setEmail("user01@test.org");
//        member.setName("(수정)사용자01");
//        mapper.update(member);

        mapper.delete("user01@test.org");

        //List< Member> members = mapper.getList();
        //members.forEach(System.out::println);
    }
}
