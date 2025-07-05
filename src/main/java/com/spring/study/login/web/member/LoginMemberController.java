package com.spring.study.login.web.member;

import com.spring.study.login.domain.member.LoginMember;
import com.spring.study.login.domain.member.LoginMemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class LoginMemberController {
    private final LoginMemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") LoginMember member) {
        return "login/members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute LoginMember member, BindingResult result) {
        if (result.hasErrors()) {
            return "login/members/addMemberForm";
        }
        memberRepository.save(member);
        return "redirect:/";
    }
}
