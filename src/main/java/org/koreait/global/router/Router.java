package org.koreait.global.router;

import org.koreait.global.configs.ControllerConfig;
import org.koreait.main.controllers.MainController;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Router {

    private static Router instance;

    // 컨트롤러 객체를 담아줄 컨테이너 객체
    private final Map<Class<?>, Controller> controllers;

    private Router() {
        controllers = new HashMap<>();

        ControllerConfig config = new ControllerConfig();
        Method[] methods = config.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                Object obj = method.invoke(config);
                if (obj instanceof Controller con) {
                    controllers.put(obj.getClass(), con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static Router getInstance() {
        if (instance == null) {
            instance = new Router();
        }

        return instance;
    }

    /**
     * 컨트롤러 화면 전환
     *
     */
    public void _change(Class<?> clazz) {
        Controller controller = controllers.get(clazz);
        if (controller == null) {
            System.out.println("등록되지 않은 화면입니다.");
            return;
        }

        controller.run();
    }

    public static void change(Class<?> clazz) {
        getInstance()._change(clazz);
    }

    public static void start() {
        change(MainController.class); // 첫 화면은 메인 메뉴
    }
}
