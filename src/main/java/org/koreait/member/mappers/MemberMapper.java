package org.koreait.member.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.koreait.global.paging.SearchForm;
import org.koreait.member.entities.Member;

import java.util.List;
import java.util.Optional;

public interface MemberMapper {
    int register(Member member);
    int update(Member member);
    int delete(String email);

    Optional<Member> get(String email);
    List<Member> getList(SearchForm search);

    @Select("SELECT COUNT(*) FROM member WHERE email=#{email}")
    int exists(@Param("email") String email);
}
