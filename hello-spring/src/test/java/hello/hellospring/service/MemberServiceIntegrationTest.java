package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 데이터를 롤백 하기 때문에 남아 있는 것이 없음 // 다음 테스트 용이
class MemberServiceIntegrationTest {

    // test 는 필드 기반으로 받는 것이 편함 (@Autowired)
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository; // clear 쓰기 위해 가져옴

    @Test
    void 회원가입() {   // given, when, then 으로 주석 달고 하면 좋음
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member); // memberService에 join을 검증

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // memberService.join(member2)를 넣으면 IllegalStateException.class(예외) 발생
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

        //then
    }
}