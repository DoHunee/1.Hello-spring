package hello.hellospring.domain;


/*  
1. 도메인에서  회원 객체를 생성!!
*/

 public class Member {

  private Long id;
  private String name;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
}
