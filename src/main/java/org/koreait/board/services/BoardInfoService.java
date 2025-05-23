package org.koreait.board.services;

import org.koreait.board.entities.Board;
import org.koreait.board.exceptions.BoardNotFoundException;
import org.koreait.board.mappers.BoardMapper;
import org.koreait.global.configs.DBConn;
import org.koreait.global.paging.SearchForm;

import java.util.List;

public class BoardInfoService {

    private  BoardMapper mapper;

    public BoardInfoService() {
        updateMapper();
    }

    public void updateMapper() {
        // 조회는 매번 갱신이 필요하므로 새로 mapper  생성
        mapper = DBConn.getInstance().getSession().getMapper(BoardMapper.class);
    }

    /**
     * 게시글 번호로 하나 조회
     *
     * @param seq
     * @return
     */
    public Board get(long seq) {
        updateMapper();
        return mapper.get(seq).orElseThrow(() -> new BoardNotFoundException("게시글을 찾을 수 없습니다."));
    }

    /**
     * 목록 조회
     *
     * @param search
     * @return
     */
    public List<Board> getList(SearchForm search) {
        updateMapper();
        // 페이징 기본값 처리 S
        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();
        limit = limit < 1 ? 10 : limit;
        int offset = (page - 1) * limit;

        search.setPage(page);
        search.setOffset(offset);
        search.setLimit(limit);

        // 페이징 기본값 처리 E
        return mapper.getList(search);
    }

    public BoardMapper getMapper() {
        return null;
    }
}

