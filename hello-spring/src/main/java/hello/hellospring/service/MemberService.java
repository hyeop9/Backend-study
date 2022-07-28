package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 회원 서비스 만들기
// @Service // 스프링 컨테이너에 등록하는 역할
public class MemberService { // 단축키 command + shift + t 로 테스트 만들기

    private final MemberRepository memberRepository;

    // 같은 인스턴스를 사용하기 위해 new를 사용해서 새로 만드는 것이 아니라 외부에서 입력을 받도록 함
    // @Autowired
    public MemberService(MemberRepository memberRepository) { // 생성자를 통해서 들어옴 ==> 생성자 주입
        this.memberRepository = memberRepository;
    }

    // 화원가입
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증

        memberRepository.save(member);
        return member.getId(); // 아이디 반환
    }
    private void validateDuplicateMember(Member member) { // 중복 회원 찾기
        memberRepository.findByName(member.getName()) // 단축키 command + option + V 자동 리턴 // 앞에 "Optional<Member> result =" 생략
                .ifPresent(m -> { // 만약에 값이 있으면...
                    throw new IllegalStateException("이미 존재하는 회원입니다"); // ...이 로직 동작 // Optional을 사용해서 가능
                });
    }
    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
