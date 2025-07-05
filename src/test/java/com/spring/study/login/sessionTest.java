package com.spring.study.login;

import com.spring.study.login.domain.member.LoginMember;
import com.spring.study.login.web.session.SessionManager;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class sessionTest {
    SessionManager sessionManager = new SessionManager();

    @Test
    void sesisonTest() {
        //HttpServletResponse 객체를 생성하기 어렵기 때문에 비슷한 역할을 해주는 가짜 생성
        MockHttpServletResponse response = new MockHttpServletResponse();
        LoginMember member = new LoginMember();

        //세션 생성
        sessionManager.createSession(member, response);

        //요청에 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //세션 조회
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        //세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }
}
