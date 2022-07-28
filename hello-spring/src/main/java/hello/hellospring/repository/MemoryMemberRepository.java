package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 구현체 생성
// @Repository
public class MemoryMemberRepository implements MemberRepository{ // 단축키 option + enter

    private static Map<Long, Member> store = new HashMap<>(); // 회원 저장, 실무에서는 다른 Map 선호 (동시성 문제)
    private static long sequence = 0L; // 0,1,2 등 키값 생성, 실무에서는 long보다 다른 것을 사용

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id 셋팅
        store.put(member.getId(), member); // store에 저장 (==Map에 저장)
        return member; // 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) { // 아이디 찾기
        return Optional.ofNullable(store.get(id)); // Null이 나올 수 있기 때문에 Optional로 감싸준다
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // get.name()이 파라미터로 넘어온 name과 같으면 필터링
                .findAny(); // 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store에 있는 values(==member)을 반환
    }

    public void clearStore() {
        store.clear();
    }
}
