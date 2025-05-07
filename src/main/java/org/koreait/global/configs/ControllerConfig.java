package org.koreait.global.configs;

import org.koreait.board.controllers.BoardController;
import org.koreait.board.controllers.BoardRegisterController;
import org.koreait.board.controllers.BoardUpdateController;
import org.koreait.board.services.BoardInfoService;
import org.koreait.board.services.BoardRegisterService;
import org.koreait.board.services.BoardSaveService;
import org.koreait.global.services.Bean;
import org.koreait.global.services.ServiceContainer;
import org.koreait.main.controllers.MainController;
import org.koreait.member.controllers.*;
import org.koreait.member.services.MemberJoinService;
import org.koreait.member.services.MemberLoginService;
import org.koreait.member.services.MemberUpdateService;

public class ControllerConfig {

    // 메인 화면
    public MainController mainController() {
        return new MainController();
    }

    /* 회원 관련 S */
    // 회원관리 메인메뉴
    public MemberController memberController() {
        return new MemberController();
    }

    // 회원가입
    public MemberJoinController joinController() {
        MemberJoinService service = ServiceContainer.getBean(MemberJoinService.class);
        return new MemberJoinController(service);
    }

    // 로그인
    public MemberLoginController loginController() {
        MemberLoginService service = ServiceContainer.getBean(MemberLoginService.class);
        return new MemberLoginController(service);
    }

    // 회원정보
    public MemberInfoController infoController() {
        return new MemberInfoController();
    }

    // 회원정보 수정
    public MemberUpdateController updateController() {
        MemberUpdateService service = ServiceContainer.getBean(MemberUpdateService.class);
        return new MemberUpdateController(service);
    }
    /* 회원 관련 E */

    /* 게시판 관련 S */
    public BoardController boardController() {
        return new BoardController();
    }

    @Bean
    public BoardUpdateController boardUpdateController() {
        BoardInfoService infoService = ServiceContainer.getBean(BoardInfoService.class);
        BoardSaveService saveService = ServiceContainer
                .getBean(BoardSaveService.class);
        return new BoardUpdateController(infoService, saveService);
    }


    /* 게시판 관련 E */

    // 게시판 글등록
    public BoardRegisterController boardRegisterController() {
        BoardRegisterService service = ServiceContainer.getBean(BoardRegisterService.class);
        return new BoardRegisterController(service);
    }
}
