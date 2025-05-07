package org.koreait.board.services;

import org.koreait.board.entities.Board;
import org.koreait.board.exceptions.BoardNotFoundException;
import org.koreait.board.mappers.BoardMapper;

public class BoardDeleteService {
    private final BoardMapper mapper;

    public BoardDeleteService(BoardMapper mapper) {
        this.mapper = mapper;
    }

    public void delete(long seq) {
        Board board = mapper.get(seq).orElseThrow(() -> new BoardNotFoundException("게시글을 찾을 수 없습니다."));
        mapper.delete(seq); // 실제 삭제
    }
}