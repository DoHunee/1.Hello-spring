package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;

/* 
@BeforeEach : 각 테스트 실행 전에 호출된다. 
테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고, 
의존관계도 새로 맺어준다
*/


// 이쪽은 스프링이랑 엮는거 없이 순수하게 JAVA code만 테스트 하는거야!!
class MemberServiceTest {
  MemberService memberService;
  MemoryMemberRepository memberRepository;

  @BeforeEach
  // 지금 memberservice 입장으로는 직접 new 하지 않고 외부에 memberRepository를 외부에서 넣어주고 있다
  // 이런것을 DI 라고 한다.
  // main/service/MemberService.java 에서 보면 memberRepository를 주입받고 있다.
  public void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  public void afterEach() {
    // 월할한 테스트를 위한 메모리 DB 초기화
    memberRepository.clearStore();
  }

  @Test
  public void 회원가입() throws Exception {
    // Given  이게 주어졌을떄!
    Member member = new Member();
    member.setName("hello");
    
    // When  이런 상황때!!
    Long saveId = memberService.join(member);  // 저장한 아이디가 나와야겠지!!
    
    // Then  이런 데이터가 나와야 한다!
    Member findMember = memberRepository.findById(saveId).get();
    assertEquals(member.getName(), findMember.getName());  // getName()으로 받은 이름과 저장된 이름이 같은지 확인!
  }

  @Test
  public void 중복_회원_예외() throws Exception {
    // Given 이게 주어졌을떄!
    // member1과 member2 두 개의 Member 객체를 생성하고 이름을 "spring"으로 설정합니다.
    Member member1 = new Member();
    member1.setName("spring");
    Member member2 = new Member();
    member2.setName("spring");
    
    
    // When 이런 상황때!!
    
    // < case1 >
    // memberService.join(member1); 
    // try {
    //   memberService.join(member2);
    //   fail();
    // } catch (IllegalStateException e) {
    //   assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    //   return;
    // }

    // < case2 >
    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class,
        () -> memberService.join(member2));// 예외가 발생해야 한다.
    
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    
  }
}