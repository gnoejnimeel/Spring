package com.spring.study.login.domain.login;

import com.spring.study.login.domain.member.LoginMember;
import com.spring.study.login.domain.member.LoginMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private LoginMemberRepository memberRepository;

    public LoginMember login(String loginId, String password) {
        //null이면 로그인 실패
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
