package org.koreait.board.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.koreait.board.entities.Board;
import org.koreait.global.configs.DBConn;

public class BoardMapperTest {
    private BoardMapper mapper;

    @BeforeEach
    void init(){
        mapper = DBConn.getInstance().getSession().getMapper(BoardMapper.class);
    }

    @Test
    void test1(){
        Board board = new Board();
        board.setPosterId(1);  // 로그인한 사용자 ID
        board.setSubject("테스트 제목입니다.");
        board.setContent("이것은 테스트 게시글 내용입니다.");
        //board.setRegDt(LocalDateTime.now());
        //board.setModDt(LocalDateTime.now());

        mapper.register(board);
        System.out.println("등록된 게시글 번호 (seq): " + board.getSeq());

    }
}
