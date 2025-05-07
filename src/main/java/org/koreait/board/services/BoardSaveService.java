package org.koreait.board.services;

import org.koreait.board.controllers.RequestBoard;
import org.koreait.board.entities.Board;
import org.koreait.board.mappers.BoardMapper;
import org.koreait.global.validators.Validator;
import org.koreait.member.MemberSession;
import org.koreait.member.entities.Member;

public class BoardSaveService {
    private final BoardMapper mapper;
    private final Validator<Board> validator;

    public BoardSaveService(BoardMapper mapper, Validator<Board> validator) {
        this.mapper = mapper;
        this.validator = validator;
    }

    public void process(RequestBoard form) {
    }
}

/**
 * 게시글 저장 처리 (작성자 정보 포함)
 * @param*
*/