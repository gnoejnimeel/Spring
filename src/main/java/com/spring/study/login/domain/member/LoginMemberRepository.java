package com.spring.study.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class LoginMemberRepository {
    private static Map<Long, LoginMember> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용

    public LoginMember save(LoginMember member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public LoginMember findById(Long id) {
        return store.get(id);
    }

    public Optional<LoginMember> findByLoginId(String loginId) {
        return findAll()
                .stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<LoginMember> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
