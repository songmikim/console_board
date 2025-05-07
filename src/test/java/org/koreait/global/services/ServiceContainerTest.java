package org.koreait.global.services;

import org.junit.jupiter.api.Test;
import org.koreait.member.services.MemberJoinService;
import org.koreait.member.services.MemberLoginService;

public class ServiceContainerTest {

    @Test
    void test1() {
        MemberJoinService joinService = ServiceContainer.getBean(MemberJoinService.class);
        System.out.println(joinService);

        MemberLoginService loginService = ServiceContainer.getBean(MemberLoginService.class);
        System.out.println(loginService);

        MemberLoginService loginService2 = ServiceContainer.getBean(MemberLoginService.class);
        System.out.println(loginService == loginService2); // 싱글톤
    }
}
