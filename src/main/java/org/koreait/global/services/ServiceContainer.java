package org.koreait.global.services;

import org.koreait.board.services.BoardService;
import org.koreait.member.services.MemberService;

import java.lang.reflect.Method;
import java.util.*;

public class ServiceContainer {

    private static ServiceContainer instance;

    // 생성된 객체를 담을 컨테이너 공간
    private final Map<Class<?>, Object> objects;

    // 동적으로 객체를 생성하기 위한 설정이 담겨 있는 Class 클래스 객체
    private final Set<Class<?>> classes;

    private ServiceContainer() {
        objects = new HashMap<>();
        classes = new HashSet<>();
    }

    // 동적으로 로드할 객체가 설정되어 있는 설정 Class 클래스 객체를 추가
    public void register(Collection<Class<?>> classes) {
        this.classes.addAll(classes);
    }

    // 동적으로 로드할 객체가 설정되어 있는 설정 Class 클래스 객체를 추가
    public void register(Class<?>... classes) {
        Collections.addAll(this.classes, classes);
    }

    /**
     * 동적 객체 생성
     *
     * 객체를 동적으로 생성해서 objects 컨테이너에 담아준다, 필요할때 Class 클래스 정보를 가지고 조회
     */
    public void init() {
        for (Class<?> clazz : classes) {
            // @Configuration 애노테이션이 정의된 클래스만 생성할 객체를 설정한다.
            if (clazz.getAnnotation(Configuration.class) == null) continue;
            try {
                // 동적 객체 생성
                Object obj = clazz.getDeclaredConstructor().newInstance();
                Method[] methods = obj.getClass().getDeclaredMethods();
                for (Method method : methods) {
                    // @Bean이 적용된 메서드만 동적 호출하여 객체 생성
                    if (method.getAnnotation(Bean.class) == null) continue;

                    // 동적 메서드 호출해서 컨테이너가 관리할 객체 생성
                    Object bean = method.invoke(obj);

                    // 생성된 객체를 컨테이너 저장 공간에 저장
                    objects.put(bean.getClass(), bean);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 컨테이너에 생성되어 있는 객체를 조회
     *
     * @param clazz
     * @return
     * @param <T>
     */
    public <T> T _getBean(Class<T> clazz) {
        return (T)objects.get(clazz);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getInstance()._getBean(clazz);
    }

    public static ServiceContainer getInstance() {
        if (instance == null) {
            instance = new ServiceContainer();
            instance.register(MemberService.class, BoardService.class);
            instance.init();
        }

        return instance;
    }
}
