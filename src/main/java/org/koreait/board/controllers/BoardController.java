package org.koreait.board.controllers;

import org.koreait.global.router.Controller;
import org.koreait.global.router.Router;

import java.util.List;
import static org.koreait.member.MemberSession.isLogin;
public class BoardController extends Controller {

    @Override
    public void show() {
        setMenus(isLogin() ? List.of("1", "2", "3") : List.of("1"));

        StringBuffer sb = new StringBuffer(2500);
        if (isLogin()) {
            sb.append("1. 게시글 목록\n")
                    .append("2. 게시글 등록\n")
                    .append("3. 게시글 상세 조회");
        } else {
            sb.append("1. 게시글 목록"); // 비회원은 목록만 볼 수 있음
        }
        System.out.println(sb);
    }

    @Override
    public void process(String command) {
        int menu = Integer.parseInt(command);
        System.out.println(menu + "===================================");
        switch (menu) {
            case 1:
                // Router.change(BoardListController.class);  // 목록
                break;
            case 2:
                Router.change(BoardRegisterController.class); // 등록
                //if (isLogin()) Router.change(BoardRegisterController.class); // 등록
                break;
            case 3:
                // if (isLogin()) Router.change(BoardViewController.class); // 상세 조회
                break;
        }
    }
}
