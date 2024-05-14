package hello.hellospring.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

  // hello.html를 맵핑하는!!
  // http://localhost:8080/hello
  @GetMapping("hello")
  public String hello(Model model) {
    model.addAttribute("data", "hello!"); // data에 hello라는 스트링을 담은거네~~
    return "hello";
  }

  // mvc 패턴을 사용해서 hello-template.html를 맵핑하는!!
  // http://localhost:8080/hello-mvc?name=임의의데이터
  @GetMapping("hello-mvc")
  public String helloMvc(@RequestParam("name") String name, Model model) {
  model.addAttribute("name", name);
  return "hello-template";
  }

  // 이렇게 접근해서 페이지 소스 보면 only 데이터만 존재!! => 자주 쓰지 않는다!
  // http://localhost:8080/hello-string?name=임의의데이터
  @GetMapping("hello-string")
  @ResponseBody
  public String helloString(@RequestParam("name") String name) {
  return "hello " + name;
  }

  // API 방식으로 데이터를 json방식으로 전달하는 방법 => 가장 많이 이용할 방법들!!
  // http://localhost:8080/hello-api?name=임의의데이터
  @GetMapping("hello-api")
  @ResponseBody
  public Hello helloApi(@RequestParam("name") String name) {
    Hello hello = new Hello();
    hello.setName(name);
    return hello;
  }

  static class Hello {
    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }



}
