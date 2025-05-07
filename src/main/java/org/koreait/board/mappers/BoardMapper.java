package org.koreait.board.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.koreait.board.entities.Board;
import org.koreait.global.paging.SearchForm;

import java.util.List;
import java.util.Optional;

public interface BoardMapper {
    // 게시글 등록
    int register(Board board);

    // 게시글 수정
    int update(Board board);

    // 게시글 삭제 (id 기준)
    int delete(Long id);

    // 게시글 1개 조회 (id 기준)
    Optional<Board> get(Long id);

    // 게시글 목록 조회 (페이징 + 검색)
    List<Board> getList(SearchForm search);

    // 게시글 수 조회 (검색 기준)
    int getTotal(SearchForm search);
}
