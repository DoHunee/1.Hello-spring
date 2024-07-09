package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;



/*  
5. 회원 서비스 개발  
실질적인 로직이 돌아가는곳!!
*/


@Transactional
public class MemberService {
  
  private final MemberRepository memberRepository;  // 레포지토리를 주입받음

  // 멤버 서비스 레포지토리를 직접 생성하는게 아니라 외부에서 주입받아서 사용하도록 수정한다!!
  // 왜냐하면 테스트 코드를 작성할때는 레포지토리가 필요 없기 때문이다!!

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  // 회원가입
  public Long join(Member member) {

    /*
    // 이런식으로 코드를 짜고 => 메소드로 리팩토링 할 수 있다!
    // 마우스 우클릭 => 리팩토링!!
    memberRepository.findByName(member.getName())
    .ifPresent(m -> {
           throw new IllegalStateException("이미 존재하는 회원입니다.");
         });
     */

    long start = System.currentTimeMillis(); // 시작시간 저장
    
    try {
      validateDuplicateMember(member); // 중복 회원 검증 => 비즈니스 로직 추가!
      memberRepository.save(member); // 검증 통과하면 memberRepository에 회원 저장
      return member.getId(); // 가입한 회원의 ID 반환!
    } 
    finally {
      long end = System.currentTimeMillis(); // 종료시간 저장
      long timeMs = end - start; // 소요시간 계산
      System.out.println("join() 호출에 걸린 시간 : " + (timeMs) + "ms"); // 총 소요시간 출력
    }

  }


  // 중복 회원 예외 
  private void validateDuplicateMember(Member member) {
    long start = System.currentTimeMillis(); // 시작 시간 측정
  
    memberRepository.findByName(member.getName())
        .ifPresent(m -> {
          throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
  
    long end = System.currentTimeMillis(); // 종료 시간 측정
    long timeMs = end - start; // 걸린 시간 계산
    System.out.println("validateDuplicateMember() 호출에 걸린 시간: " + timeMs + "ms"); // 걸린 시간 출력
  }


  // 전체 회원 조회 
  public List<Member> findMembers() {
    long start = System.currentTimeMillis(); // 시작 시간 측정

    List<Member> members = memberRepository.findAll();

    long end = System.currentTimeMillis(); // 종료 시간 측정
    long timeMs = end - start; // 걸린 시간 계산
    System.out.println("findMembers() 호출에 걸린 시간: " + timeMs + "ms"); // 걸린 시간 출력

    return members;
}

  
  // 회원 조회
  public Optional<Member> findOne(Long memberId) {
    long start = System.currentTimeMillis(); // 시작 시간 측정

    Optional<Member> member = memberRepository.findById(memberId);

    long end = System.currentTimeMillis(); // 종료 시간 측정
    long timeMs = end - start; // 걸린 시간 계산
    System.out.println(" findOne 호출에 걸린 시간: " + timeMs + "ms"); // 걸린 시간 출력

    return member;
}


}