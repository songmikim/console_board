package org.koreait.board.services;

import org.koreait.board.entities.Board;
import org.koreait.board.exceptions.BoardNotFoundException;
import org.koreait.board.mappers.BoardMapper;
import org.koreait.global.paging.SearchForm;

import java.util.List;

public class BoardInfoService {
    private final BoardMapper mapper;

    public BoardInfoService(BoardMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 게시글 ID로 게시글 1개 조회
     *
     * @param id 게시글 ID
     * @return 조회된 게시글
     */
    public Board get(Long id) {
        return mapper.get(id).orElseThrow(() -> new BoardNotFoundException("해당 게시글이 존재하지 않습니다."));
    }

    /**
     * 게시글 목록 조회 (검색 + 페이징)
     *
     * @param search 검색 조건 및 페이징 정보
     * @return 게시글 목록
     */
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
