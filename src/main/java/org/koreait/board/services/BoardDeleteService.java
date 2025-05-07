package org.koreait.board.services;

import org.koreait.board.entities.Board;
import org.koreait.board.exceptions.BoardNotFoundException;
import org.koreait.board.mappers.BoardMapper;

import java.util.Optional;

public class BoardDeleteService {
    private final BoardMapper mapper;

    public BoardDeleteService(BoardMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 게시글 ID를 받아 해당 게시글을 삭제
     *
     * @param seq 삭제할 게시글의 ID
     */
    public void delete(Long seq) {
        Optional<Board> board = mapper.get(seq);  // 게시글 조회
        if (board == null) {
            throw new BoardNotFoundException("삭제할 게시글이 존재하지 않습니다.");
        }

        mapper.delete(seq); // 실제 삭제 처리
    }
}