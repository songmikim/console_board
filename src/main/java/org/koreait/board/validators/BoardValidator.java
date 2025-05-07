package org.koreait.board.validators;

import org.koreait.board.entities.Board;
import org.koreait.global.validators.Validator;

public class BoardValidator implements Validator<Board> {
    @Override
    public void check(Board form) {
        if (form.getSubject() == null || form.getSubject().isBlank()) {
            throw new IllegalArgumentException("제목은 필수입니다.");
        }
        if (form.getContent() == null || form.getContent().isBlank()) {
            throw new IllegalArgumentException("내용은 필수입니다.");
        }
    }
}
