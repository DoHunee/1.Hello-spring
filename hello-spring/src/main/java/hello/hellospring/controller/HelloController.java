package hello.hellospring.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HelloController {

    // hello.html를 맵핑하는!!
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!"); // data에 hello라는 스트링을 담은거네~~
        return "hello";
    }
}