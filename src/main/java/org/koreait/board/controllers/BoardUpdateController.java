package org.koreait.board.controllers;


import org.koreait.board.entities.Board;
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
        //System.out.println("수정할 항목을 선택하세요(m - 메인메뉴, q - 종료).");
        //System.out.println("1. 제목, 2. 내용");
        System.out.println("수정할 게시글 번호를 입력하세요 (m - 메인메뉴, q - 종료):");
    }

    @Override
    public  void common() {
        System.out.println("******************* 게시글 수정 *******************");
    }

    @Override
    public void process(String command) {
        Scanner sc = new Scanner(System.in);

        // 게시글 번호 입력
        long inputSeq;
        try {
            inputSeq = Long.parseLong(command);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 번호입니다.");
            return;
        }

        Board form = new Board();
        form.setSeq(inputSeq);  // 입력 받은 번호 설정

        System.out.println("수정할 항목을 선택하세요:");
        System.out.println("1. 제목, 2. 내용");
        String menuInput = sc.nextLine();

        System.out.print("수정할 내용을 입력하세요: ");
        String contentInput = sc.nextLine();

        switch (menuInput) {
            case "1":
                form.setSubject(contentInput); break;
            case "2":
                form.setContent(contentInput); break;
            default:
                System.out.println("잘못된 선택입니다.");
                return;
        }

        saveService.process(form); // BoardUpdateService 호출

        // 이후 게시글 보기로 이동
        BoardViewController.setSeq(inputSeq);
        Router.change(BoardViewController.class);
    }
}
