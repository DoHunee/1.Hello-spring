package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
  
  // @Autowired는 필드에 종속성을 직접 주입합니다
  @Autowired
  MemberService memberService;
  @Autowired
  MemberRepository memberRepository;

  // @Transactiona 이게 없으면 바로 DB에 들어간다!!!
  // Test를 여러번 할 수 있게 하기 위해 @Transactional이 필요하다! => 굳이 BeforeEach,AfterEach를 쓸 필요가 없다 .
  
  @BeforeEach
  void clearMemberTable() {
    memberRepository.deleteAll();
}
  
  @Test
  public void 회원가입() throws Exception {
    // Given
    Member member = new Member();
    member.setName("spring"); // DB에 spring이라는 회원이 있다면 안되겠지!!!

    // When: 멤버 서비스의 join 메소드 호출
    Long saveId = memberService.join(member); 
    
    // Then: 저장된 멤버를 ID로 조회하고, 이름이 동일한지 확인 
    Member findMember = memberRepository.findById(saveId).get();
    assertEquals(member.getName(), findMember.getName());
  }

  @Test
  public void 중복_회원_예외() throws Exception {
    // Given
    Member member1 = new Member();
    member1.setName("spring");
    Member member2 = new Member();
    member2.setName("spring");
    
    // When: 첫 번째 멤버를 가입시키고, 두 번째 멤버 가입 시도 (예외 발생 예상)
    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class,
        () -> memberService.join(member2));// 예외가 발생해야 한다.

    // Then: 예외 메시지가 "이미 존재하는 회원입니다."와 동일한지 확인
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  }
  
  @Test
  public void 전체_회원_조회() throws Exception {
    // Given: 여러 회원 등록
    memberRepository.deleteAll(); // Ensure clean state

    Member member1 = new Member();
    member1.setName("spring1");
    memberService.join(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    memberService.join(member2);

    // When: 모든 회원 조회
    List<Member> result = memberService.findMembers();

    // Debug: Print members to see what's in the database
    result.forEach(member -> System.out.println(member.getName()));

    // Then: 등록된 회원 수 확인
    assertThat(result.size()).isEqualTo(2);
  }

  @Test
  public void 회원_조회() throws Exception {
      // Given: 특정 회원 등록
      Member member = new Member();
      member.setName("spring");
      Long savedId = memberService.join(member);

      // When: ID로 회원 조회
      Optional<Member> result = memberService.findOne(savedId);

      // Then: 조회된 회원의 이름 확인
      result.ifPresent(findMember -> assertEquals("spring", findMember.getName()));
      assertTrue(result.isPresent(), "회원이 조회되어야 합니다.");
  }

  
}

