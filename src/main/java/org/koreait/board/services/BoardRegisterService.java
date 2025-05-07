package org.koreait.board.services;

import org.koreait.board.entities.Board;
import org.koreait.board.mappers.BoardMapper;
import org.koreait.global.validators.Validator;

import java.time.LocalDateTime;

public class BoardRegisterService {
    private final Validator<Board> validator;
    private final BoardMapper mapper;

    public BoardRegisterService(Validator<Board> validator, BoardMapper mapper) {
        this.validator = validator;
        this.mapper = mapper;
    }

    public void process(Board form, String poster) {
        validator.check(form);

        Board board = new Board();
        board.setPoster(poster); // 로그인 사용자 ID
        board.setSubject(form.getSubject());
        board.setContent(form.getContent());
        board.setRegDt(LocalDateTime.now());
        board.setModDt(LocalDateTime.now());

        mapper.register(board);
    }
}
