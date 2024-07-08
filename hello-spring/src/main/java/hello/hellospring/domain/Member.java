package hello.hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*  
1. 도메인에서  회원 객체를 생성!!
*/

@Entity
public class Member {

  @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
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
