package org.koreait.member.validators;

import org.koreait.global.validators.EmailValidator;
import org.koreait.global.validators.MobileValidator;
import org.koreait.global.validators.RequiredFieldValidator;
import org.koreait.global.validators.Validator;
import org.koreait.member.controllers.RequestJoin;
import org.koreait.member.mappers.MemberMapper;

public class MemberJoinValidator implements Validator<RequestJoin>, RequiredFieldValidator, EmailValidator, MobileValidator {

    private final MemberMapper mapper;

    public MemberJoinValidator(MemberMapper mapper) {
        this.mapper = mapper;
    }
    /**
     * 회원가입 데이터 유효성 검사
     * 1. 필수항목 - 이메일, 비밀번호, 비밀번호 확인, 휴대전화 번호, 약관 동의
     * 2. 이메일 중복 여부, 회원을 인증하는 것이 이메일이므로 중복 X
     * 3. 이메일 형식 체크
     * 4. 비밀번호는 8자리 이상, 비밀번호 확인과 일치여부
     * 5. 휴대전화 번호 형식 체크
     * @param form
     */
    @Override
    public void check(RequestJoin form) {
        String email = form.getEmail();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();
        String name = form.getName();
        String mobile = form.getMobile();
        boolean terms = form.isTerms();

        // 1. 필수항목 - null, 비어 있는 문자열 "", "   "
        checkString(email, "이메일을 입력하세요.");
        checkString(password, "비밀번호를 입력하세요.");
        checkString(confirmPassword, "비밀번호를 확인하세요.");
        checkString(name, "회원명을 입력하세요.");
        checkString(mobile, "휴대전화번호를 입력하세요.");
        checkTrue(terms, "약관에 동의해 주세요.");

        // 2. 이메일 중복 여부
        checkTrue(mapper.exists(email) == 0, "이미 가입된 이메일 입니다.");

        // 3. 이메일 형식 체크
        checkTrue(checkEmail(email), "이메일 형식이 아닙니다.");

        // 4. 비밀번호는 8자리 이상
        checkTrue(password.length() >= 8, "비밀번호는 8자리 이상 입력하세요.");

        checkTrue(password.equals(confirmPassword), "입력하신 비밀번호 확인이 일치하지 않습니다.");

        // 5. 휴대전화 번호 형식 체크
        checkTrue(checkMobile(mobile), "휴대전화번호 형식이 아닙니다.");
    }
}
