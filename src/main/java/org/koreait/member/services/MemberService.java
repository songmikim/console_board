package org.koreait.member.services;

import org.koreait.global.configs.DBConn;
import org.koreait.global.services.Bean;
import org.koreait.global.services.Configuration;
import org.koreait.member.mappers.MemberMapper;
import org.koreait.member.validators.MemberJoinValidator;
import org.koreait.member.validators.MemberLoginValidator;
import org.koreait.member.validators.MemberUpdateValidator;

/**
 * Member services 패키지에서 관리하게되는 객체에 대한 설정
 *
 */
@Configuration
public class MemberService {

    @Bean
    public MemberMapper memberMapper() {
        return DBConn.getInstance().getSession().getMapper(MemberMapper.class);
    }

    @Bean
    public MemberJoinValidator joinValidator() {
        return new MemberJoinValidator(memberMapper());
    }

    @Bean
    public MemberLoginValidator loginValidator() {
        return new MemberLoginValidator(infoService());
    }

    @Bean
    public MemberUpdateValidator updateValidator() {
        return new MemberUpdateValidator();
    }

    @Bean
    public MemberInfoService infoService() {
        return new MemberInfoService(memberMapper());
    }

    @Bean
    public MemberJoinService joinService() {
        return new MemberJoinService(joinValidator(), memberMapper());
    }

    @Bean
    public MemberLoginService loginService() {
        return new MemberLoginService(loginValidator(), infoService());
    }

    @Bean
    public MemberUpdateService updateService() {
        return new MemberUpdateService(memberMapper(), updateValidator());
    }

    public void common() {
        //
    }
}
