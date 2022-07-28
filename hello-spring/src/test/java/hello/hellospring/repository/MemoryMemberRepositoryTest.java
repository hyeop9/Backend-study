package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {   // MemoryMemberRepository에 선언된 clearStore로 테스트가 하나씩 끝날 때마다 저장소를 지움(순서 상관 없기 위함)
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member); // repository에 저장

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result); // Assertions.assertThat().isEqualTo() 로 비교
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1"); // spring1이라는 회원 가입
        repository.save(member1);

        Member member2 = new Member(); // 단축키 shift + F6 전체 rename
        member2.setName("spring2"); // spring2이라는 회원 가입
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2); // result 값이 두 개가 나와야 한다
    }
}
