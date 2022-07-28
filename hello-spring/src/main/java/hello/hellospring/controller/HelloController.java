package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { //외부에서 받음
        model.addAttribute("name", name); // 키, name
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody  // http 응답 바디에 직접 넣는다
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;  // spring이라고 하면 "hello spring", html은 안나오고 데이터를 그대로 보냄
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // 객체 생성
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;

        // getter, setter (단축키: control + enter)
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
