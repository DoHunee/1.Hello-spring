package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
// import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

/* 
@AfterEach : 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다. 
이렇게 되면 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있다. 
@AfterEach 를 사용하면 각 테스트가 종료될 때마다 이 기능을 실행한다. 
여기서는 메모리 DB에 저장된 데이터를 삭제한다.
테스트는 각각 독립적으로 실행되어야 한다. 
테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.
*/

class MemoryMemberRepositoryTest {
  
  // MemoryMemberRepository 인스턴스를 생성
  MemoryMemberRepository repository = new MemoryMemberRepository();

  /*
    각 테스트가 끝난 후에 실행되어 저장소를 초기화하는 메서드
    @AfterEach 애너테이션을 사용하여 각 테스트 후에 실행
   */
  @AfterEach
  public void afterEach() {
    repository.clearStore(); // 저장소를 초기화하여 테스트 간 간섭을 방지
  }


  //회원 저장 기능을 테스트하는 메서드
  @Test
  public void save() {
    // given: 테스트 준비 단계
    Member member = new Member();
    member.setName("spring");
    
    // when: 테스트 실행 단계
    repository.save(member);
    
    // then: 테스트 검증 단계
    Member result = repository.findById(member.getId()).get();
    assertThat(result).isEqualTo(member); // 저장된 회원이 올바르게 조회되는지 검증
  }


  // ID로 회원을 찾는 기능을 테스트하는 메서드
  @Test
  public void findById() {
    // given : 테스트 준비 단계
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);
    
    // when: 테스트 실행 단계
    Member result = repository.findById(member1.getId()).get();
    
    // then: 테스트 검증 단계
    assertThat(result).isEqualTo(member1); // Verify that the correct member is searched by ID
  }
  
   
  //이름으로 회원을 찾는 기능을 테스트하는 메서드 
  @Test
  public void findByName() {
    // given : 테스트 준비 단계
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);
    
    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);
    
    // when: 테스트 실행 단계
    Member result = repository.findByName("spring1").get();
    
    // then: 테스트 검증 단계
    assertThat(result).isEqualTo(member1); // 이름으로 올바른 회원이 조회되는지 검증
  }


  // 모든 회원을 찾는 기능을 테스트하는 메서드
  @Test
  public void findAll() {
    // given: 테스트 준비 단계
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);
    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);
    
    // when: 테스트 실행 단계
    List<Member> result = repository.findAll();
    
    // then: 테스트 검증 단계
    assertThat(result.size()).isEqualTo(2); // 저장된 모든 회원이 올바르게 조회되는지 검증
  }
}

