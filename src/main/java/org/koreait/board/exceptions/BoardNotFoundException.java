package org.koreait.board.exceptions;

import org.koreait.global.exceptions.NotFoundException;

public class BoardNotFoundException extends NotFoundException {
  public BoardNotFoundException(String s) {
    super("게시판을 찾을 수 없습니다.");
  }
}
