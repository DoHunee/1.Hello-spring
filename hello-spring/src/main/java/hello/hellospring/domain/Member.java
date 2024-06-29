package hello.hellospring.domain;

 // 여기서 회원 객체를 만들고!!
public class Member {

  private Long id;
  private String name;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  
}
