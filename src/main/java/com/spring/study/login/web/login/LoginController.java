package com.spring.study.login.web.login;

import com.spring.study.login.domain.login.LoginService;
import com.spring.study.login.domain.member.LoginMember;
import com.spring.study.login.web.SessionConst;
import com.spring.study.login.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

//    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        LoginMember loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 마지 않습니다.");
            return "login/loginForm";
        }

        //TODO 로그인 성공 처리

        return "redirect:/home";
    }

//    @PostMapping("/login")
    public String loginV2(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response) {
        if(bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        LoginMember loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 마지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공 처리
        //세션 관리자를 통해 세션을 생성하고, 회원 데이터 관리
        sessionManager.createSession(loginMember, response);

        return "redirect:/home";
    }

//    @PostMapping("/logout")
    public String logoutV2(HttpServletRequest request) {
        //로그아웃시 해당 세션의 정보 제거
        sessionManager.expire(request);
        return "redirect:/home";
    }

    @PostMapping("/login")
    public String loginV3(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            return "login/loginHome";
        }

        LoginMember loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 마지 않습니다.");
            return "login/loginHome";
        }

        //로그인 성공 처리
        //세션이 있으면 기존 세션 반환하고 없으면 신규 세션을 생성한다.
        HttpSession session = request.getSession(true); //기본값이 ture이다.
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        //세션 가져옴 false라서 없어도 생성하지 않는다.
        HttpSession session = request.getSession(false);
        if (session != null) {
            //세선 삭제
            session.invalidate();
        }
        return "redirect:/home";
    }
}
