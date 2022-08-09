package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // MemberService 를 가져다가 사용
    private final MemberService memberService; // new 로 생성하면 여러 컨트롤러들이 사용할 수도 있어서 X

    // 컨테이너에 등록을 하고 사용 // 생성자 호출
    @Autowired  // 스프링 컨테이너에서 MemberService 를 가져옴
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")    // 보통 데이터 등록 시 Post 사용, 조회 시 Get 사용
    public String create(MemberForm form) {
        // Member 만들기
        Member member = new Member();
        member.setName(form.getName());

        // Member 을 넘겨줌
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
