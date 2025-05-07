package org.koreait.board.services;

import org.koreait.global.paging.SearchForm;
import org.koreait.member.entities.Member;
import org.koreait.member.exceptions.MemberNotFoundException;
import org.koreait.member.mappers.MemberMapper;

import java.util.List;

public class BoardInfoService {
    private final MemberMapper mapper;

    public BoardInfoService(MemberMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 번호로 게시글을 조회
     *
     * @param email
     * @return
     */
    public Member get(String email) {
        return mapper.get(email).orElseThrow(MemberNotFoundException::new);
    }

    public List<Member> getList(SearchForm search) {
        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();
        limit = limit < 1 ? 10 : limit;
        int offset = (page - 1) * limit;
        search.setPage(page);
        search.setLimit(limit);
        search.setOffset(offset);

        return mapper.getList(search);
    }
}
