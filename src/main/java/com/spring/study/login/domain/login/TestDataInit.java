package com.spring.study.login.domain.login;

import com.spring.study.login.domain.item.LoginItem;
import com.spring.study.login.domain.item.LoginItemRepository;
import com.spring.study.login.domain.member.LoginMember;
import com.spring.study.login.domain.member.LoginMemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final LoginItemRepository itemRepository;
    private final LoginMemberRepository memberRepository;

    @PostConstruct
    public void init() {
        itemRepository.save(new LoginItem("itemA", 10000, 10));
        itemRepository.save(new LoginItem("itemB", 20000, 20));
        LoginMember member = new LoginMember();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");
        memberRepository.save(member);
    }
}
