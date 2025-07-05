package com.spring.study.login.web;

import com.spring.study.login.domain.member.LoginMember;
import com.spring.study.login.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class LoginHomeController {
    private final SessionManager sessionManager;


//    @GetMapping("/home")
    public String home() {
        return "login/home";
    }

//    @GetMapping("/home")
    public String homeLoginV2(HttpServletRequest request, Model model) {
        //세션 관리자에 저장된 회원 정보 조회
        LoginMember member = (LoginMember)sessionManager.getSession(request);
        if (member == null) {
            return "login/home";
        }

        //로그인
        model.addAttribute("member", member);
        return "login/home";
    }

//    @GetMapping("/home")
    public String homeLoginV3(HttpServletRequest request, Model model) {
        //세션 관리자에 저장된 회원 정보 조회
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "login/home";
        }

        //로그인 시점에 세션에 보관한 회원 객체를 찾는다.
        LoginMember loginMember = (LoginMember) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(loginMember == null) {
            return "login/home";
        }

        //로그인
        model.addAttribute("member", loginMember);
        return "login/loginHome";
    }

    @GetMapping("/home")
    public String homeLoginV4(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) LoginMember loginMember, Model model) {
        if(loginMember == null) {
            return "login/home";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "login/loginHome";
    }
}
