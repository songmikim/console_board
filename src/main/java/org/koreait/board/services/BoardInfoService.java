package org.koreait.board.services;

import org.koreait.board.entities.Board;
import org.koreait.board.mappers.BoardMapper;
import org.koreait.global.paging.SearchForm;
import org.koreait.member.exceptions.MemberNotFoundException;

import java.util.List;

public class BoardInfoService {
    private final BoardMapper mapper;

    public BoardInfoService(BoardMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 번호로 게시글을 조회
     *
     * @param posterId
     * @return
     */
    public Board get(int posterId) {
        return (Board) mapper.get(posterId).orElseThrow(MemberNotFoundException::new);
    }

    public List<Board> getList(SearchForm search) {
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
