package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    // 저장소
    private static Map<Long, Member> store = new HashMap<>(); // HashMap 생성
    // HashMap : 키와 값으로 구성된 Entry 객체 저장구조

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }
    // 오버라이드(재정의) : 상위(부모) 클래스에 정의된 메소드를 하위(자식) 클래스에서 다시 정의하는 것을 의미한다.
    // 메소드의 이름, 반환형, 매개변수 선언이 모두 같아야 한다.

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

}
