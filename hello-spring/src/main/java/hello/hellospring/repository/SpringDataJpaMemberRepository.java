package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


/*  
★★★★★
스프링 데이터 JPA가 JpaRepository를 상속받는 인터페이스를 자동으로 구현해준다. (이 기술을 프록시라고 한다!)
그리고 그 인터페이스를 구현한 객체를 스프링 빈으로 자동으로 등록해준다.
즉, 스프링 데이터 JPA가 자동으로 구현체를 만들어서 스프링 빈으로 등록해준다.
근데 스프링 데이터 JPA가 구현체를 만들어 등록을 한다고 했을때
개발하는 상황에 따라 구현체를 만들어 등록하는 방법이 달라질 수 있다.

그래서 스프링 데이터 JPA는 인터페이스를 두개 만들어서 구현체를 만들어 등록하는 방법을 제공한다.
사실상 인터페이스 이름으로 개발을 한다고 볼 수 있다.
*/


public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
  

  @Override
  Optional<Member> findByName(String name); //이렇게만 줘도 자동으로 JPQL select m from Member m where m.name = ? 이런식으로 쿼리를 만들어준다.
  
  
}