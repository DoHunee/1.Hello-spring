package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;

/*  
2. 레포지토리에서  인터페이스 생성!! 
*/

/* 
회원 레포지토리 인터페이스!
아래처럼 4가지 기능을 만들었다!~
optional을 쓰는 이유는 조회할 때 null을 반환할 수 있기 때문이다.
*/

public interface MemberRepository {
  Member save(Member member);  // 저장 기능
  Optional<Member> findById(Long id); //ID로 회원을 조회하는 기능
  Optional<Member> findByName(String name); //이름으로 회원을 조회하는 기능`
  List<Member> findAll(); // 모든 회원 조회하는 기능
  // void deleteAll(); 


}

// 자 이제 구현체를 만들어야겠지??
// MemoryMemberRepository.java 클래스 생성!!
