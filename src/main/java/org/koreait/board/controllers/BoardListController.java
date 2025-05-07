package org.koreait.board.controllers;

import org.koreait.board.entities.Board;
import org.koreait.board.services.BoardDeleteService;
import org.koreait.board.services.BoardInfoService;
import org.koreait.global.exceptions.CommonException;
import org.koreait.global.paging.SearchForm;
import org.koreait.global.router.Controller;

import java.util.List;
import java.util.Scanner;

public class BoardListController extends Controller {
    private final BoardInfoService service;
    private final BoardDeleteService deleteService;
    private List<Board> items;
    private SearchForm search;

    public BoardListController(BoardInfoService service,  BoardDeleteService deleteService) {
        this.service = service;
        this.deleteService = deleteService;
        search = new SearchForm();

        Scanner sc = new Scanner(System.in);
        setPrompt(() -> {
            search = new SearchForm();
            while(true) {
                try {
                    System.out.println("조회할 항목을 선택하세요.");
                    System.out.println("1. 제목, 2. 내용, 6. 게시글 삭제");
                    String sel = inputEach("1. 항목번호", sc);
                    // 선택항목 1, 2, 3, 4 중에서만 선택가능
                    if (!List.of("1","2", "6").contains(sel)) {
                        continue;
                    }
                    String sopt = null;
                    int menu = Integer.parseInt(sel);
                    switch (menu) {
                        case 1: // 제목
                            sopt = "subject"; break;
                        case 2: // 내용
                            sopt = "content"; break;
                    }

                    search.setSopt(sopt);

                    String skey = inputEach(menu == 5 ? "2. 게시글번호": "2. 검색키워드", sc);
                    search.setSkey(skey);

                    /*if (menu == 5) { // 게시글 보기 화면으로 이동
                        try {
                            // 게시글 번호 등록
                            long seq = Integer.parseInt(skey);
                            BoardViewController.setSeq(seq);
                            Router.change(BoardViewController.class);
                        } catch (NumberFormatException e) {
                            System.out.println("게시글 번호는 숫자로 입력하세요.");
                        }
                        return;
                    }*/
                    show(); // 화면 갱신

                    if (menu == 6) {
                        try {
                            long seq = Integer.parseInt(skey);
                            deleteService.delete(seq);  // 실제 삭제 처리
                            System.out.println("게시글이 성공적으로 삭제되었습니다.");
                        } catch (NumberFormatException e) {
                            System.out.println("게시글 번호는 숫자로 입력하세요.");
                        } catch (CommonException e) {
                            printError(e);
                        }
                        return;
                    }
                } catch (CommonException e) {
                    printError(e);
                }
            }
        });
    }


    @Override
    public void show() {
        // 초기 출력할 게시글 조회
        items = service.getList(search);

        printLine();
        System.out.println("게시글번호|제목|내용");
        if (items == null || items.isEmpty()) {
            System.out.println("조회된 게시글이 없습니다.");
        } else { // 게시글 출력
            items.forEach(i -> {
                System.out.printf("%d|%s|%s%n", i.getSeq(),i.getSubject(), i.getContent());
            });
        }
        printLine();
    }

    // 123
    @Override
    public void common() {
        System.out.println("************** 게시글 목록 ***************");
    }
}