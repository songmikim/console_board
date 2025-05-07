package org.koreait.board.controllers;

import org.koreait.board.entities.Board;
import org.koreait.board.services.BoardRegisterService;
import org.koreait.global.exceptions.CommonException;
import org.koreait.global.router.Controller;
import org.koreait.global.router.Router;
import org.koreait.member.MemberSession;

import java.util.Scanner;

public class BoardRegisterController extends Controller {
    private final BoardRegisterService service;

    public BoardRegisterController(BoardRegisterService service) {
        this.service = service;

        setPrompt(() -> {
            Board form = new Board();
            Scanner sc = new Scanner(System.in);

            while (true) {
                try {
                    String subject = inputEach("1. 제목", sc);
                    form.setSubject(subject);

                    String content = inputEach("2. 내용", sc);
                    form.setContent(content);

                    // 현재 로그인한 사용자의 이름을 작성자로 지정
                    int posterid = (int)MemberSession.getMember().getSeq();
                    //form.setPoster(poster);

                    service.process(form, posterid);
                    break;
                } catch (CommonException e) {
                    printError(e);
                }
            }

            // 등록 후 게시판 목록으로 이동
            Router.change(BoardController.class);
        });
    }

    @Override
    public void show() {
        System.out.println("게시글 작성을 위해 다음을 입력하세요. (메인 메뉴 - m, 종료 - q):");
    }

    @Override
    public void common() {
        System.out.println("******************* 게시글 등록 ********************");
    }
}
