package org.koreait.board.controllers;


import org.koreait.board.services.BoardInfoService;
import org.koreait.board.services.BoardSaveService;
import org.koreait.global.router.Controller;
import org.koreait.global.router.Router;

import java.util.List;
import java.util.Scanner;

public class BoardUpdateController extends Controller {



    // 게시글 수정을 위한 게시글 번호
    private  static  long seq;
    private final BoardInfoService infoService;
    private final BoardSaveService saveService;

    public BoardUpdateController(BoardInfoService infoService, BoardSaveService saveService) {
        this.infoService = infoService;
        this.saveService = saveService;

        setMenus(List.of("1", "2"));
    }

    public static void setSeq(long seq) {
        BoardUpdateController.seq = seq;
    }

    @Override
    public  void show() {
        System.out.println("수정할 항목을 선택하세요(m - 메인메뉴, q - 종료).");
        System.out.println("1. 제목, 2. 내용");

    }

    @Override
    public  void common() {
        System.out.println("******************* 게시글 수정 *******************");
    }

    @Override
    public void process(String command) {
        int menu = Integer.parseInt(command);
        RequestBoard form = new RequestBoard();

        Scanner sc = new Scanner(System.in);
        String str = inputEach("수정할 내용 입력", sc);

        switch (menu) {
            case 1: // 제목
                form.setSubject(str); break;
            case 2: // 내용1
                form.setContent(str); break;
        }

        saveService.process(form);

        BoardViewController.setSeq(seq);
        Router.change(BoardViewController.class);
    }
}
