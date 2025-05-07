package org.koreait.board.services;

import org.koreait.board.entities.Board;
import org.koreait.board.mappers.BoardMapper;
import org.koreait.global.validators.Validator;

import java.time.LocalDateTime;

public class BoardSaveService {
    private BoardMapper mapper;
    private final Validator<Board> validator;

    public BoardSaveService(BoardMapper mapper, Validator<Board> validator) {
        this.mapper = mapper;
        this.validator = validator;
    }

    public void process(Board form) {

        validator.check(form);

        /**
         * seq가 있으면 수정
         */
        if (form.getSeq() <= 0L) {
            throw new IllegalArgumentException("수정할 게시글 번호가 없습니다.");
        }

        Board item = new Board();
        item.setSeq(form.getSeq());
        item.setSubject(form.getSubject());
        item.setContent(form.getContent());
        item.setModDt(LocalDateTime.now());

        mapper.update(item);

    }
}

