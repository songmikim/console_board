package org.koreait.global.router;

import org.koreait.global.exceptions.CommonException;
import org.koreait.main.controllers.MainController;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * 추상 템플릿 메서드 패턴
 *  절차에 대한 부분은 고정
 *      공통 출력 상단 부분
 *      컨트롤러 마다 출력되는 부분
 *      명령어를 입력하는 부분
 */
public abstract class Controller {

    private List<String> menus;
    private Runnable runnable; // prompt 메서드의 기능을 대체

    // 공통 라인 출력
    public void printLine() {
        System.out.println("---------------------------------------------");
    }

    /**
     * 공통 출력 상단 부분
     * 컨트롤러에 따라서 다르게 출력되는 부분이 있다면 이 메서드를 재정의하여 변경
     */
    public void common() {
        System.out.println("******************* 메뉴 선택 ********************");
    }

    /**
     * 컨트롤러마다 다르게 출력될 부분, 재정의가 필수
     *
     */
    public abstract void show();

    private void prompt() {
        if (runnable != null) {
            runnable.run();
            return;
        }

        Scanner sc = new Scanner(System.in);
        printLine();
        while(true) {
            try {
                System.out.print("메뉴를 선택하세요(m - 메인메뉴, q - 종료): ");
                String command = sc.nextLine().toLowerCase().trim();
                processQuit(command); // 애플리케이션 종료 처리

                if (command.equals("m")) { // 메인 메뉴로 이동
                    Router.change(MainController.class);
                    break;
                }

                // 명령어가 q, m이 아닌 경우, 실제 처리가 필요한 명령어
                if (!command.isBlank() && menus != null && menus.contains(command)) {
                    process(command);
                    break;
                }


            } catch (CommonException e) {
                printError(e);
            }
        }
    }

    /**
     * 입력받은 커맨드 처리
     *
     * 필요한 컨트롤러에서 재정의하여 사용한다.
     *
     * @param command
     */
    public void process(String command) {};

    /**
     * 모든 컨트롤러는 run()을 호출해서 화면이 전환, 정의된 메서드의 순서는 변경 X
     * - 추상 템플릿 메서드 패턴
     */
    public final void run() {
        common();
        show();
        prompt();
    }

    /**
     * 애플리케이션 종료 처리
     *
     * @param command - q, quit, exit
     */
    public void processQuit(String command) {
        if (command != null && (command.equals("q") || command.equals("quit") || command.equals("exit"))) {
            System.out.println("애플리케이션을 종료 합니다.");
            System.exit(0);
        }
    }

    /**
     * 에러 메세지 출력
     *
     * @param e
     */
    public void printError(CommonException e) {
        printLine();
        System.out.printf("[%d] %s%n", e.getStatus(), e.getMessage());
    }

    /**
     * prompt 메서드에서 실행될 부분을 교체할 필요가 있는 경우
     * 하위 클래스인 컨트롤러에서 Runnable 인터페이스의 구현체를 만들어서 호출
     *
     * @param runnable
     */
    public void setPrompt(Runnable runnable) {
        this.runnable = runnable;
    }

    /**
     * 컨트롤러에 허용가능한 메뉴 범위를 설정
     *
     * @param menus
     */
    public void setMenus(List<String> menus) {
        this.menus = menus;
    }

    /**
     * 항목을 하나씩 입력 받는 메서드
     *
     * @param message
     * @param sc
     * @return
     */
    public String inputEach(String message, Scanner sc) {
       sc = Objects.requireNonNullElse(sc, new Scanner(System.in));

       while(true) {
           System.out.print(message + ": ");
           String input = sc.nextLine();
           processQuit(input); // 종료 문구가 있으면 종료 처리
           if (input != null && !input.isBlank()) {
               input = input.trim();
               if (input.equalsIgnoreCase("m")) { // 메인 메뉴로 이동
                   Router.change(MainController.class);
                   break;
               }

               return input;
           }
       }

       return null;
    }
}
