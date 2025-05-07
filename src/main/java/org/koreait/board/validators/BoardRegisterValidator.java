package org.koreait.board.validators;

import org.koreait.board.entities.Board;
import org.koreait.board.mappers.BoardMapper;
import org.koreait.global.validators.RequiredFieldValidator;
import org.koreait.global.validators.Validator;

public class BoardRegisterValidator implements Validator<Board> , RequiredFieldValidator{

    private final BoardMapper mapper;

    public BoardRegisterValidator(BoardMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void check(Board form) {
        String subject = form.getSubject();
        String content = form.getContent();

        // 1. 필수항목 - null, 비어 있는 문자열 "", "   "
        checkString(subject, "이메일을 입력하세요.");
        checkString(content, "비밀번호를 입력하세요.");
    }
}
