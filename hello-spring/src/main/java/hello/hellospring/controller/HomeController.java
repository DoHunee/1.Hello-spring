package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  
  // root 경로로 접근을 했을때 controller를 우선적으로 찾기 때문에 static폴더의 index파일보다 homme.html파일을 먼저 찾는다!!
  @GetMapping("/")
  public String home() {
    return "home"; // home.html 템플릿을 반환하는 것이다!!
  }
  
}
