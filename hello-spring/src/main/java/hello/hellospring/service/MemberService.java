package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import java.util.List;
import java.util.Optional;

/*  
5. 회원 서비스 개발  
실질적인 로직이 돌아가는곳!!
*/


public class MemberService {
  
  private final MemberRepository memberRepository;  // 레포지토리를 주입받음

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  /**
   * 회원가입
   */
  public Long join(Member member) {

    /*
    // 이런식으로 코드를 짜고 => 메소드로 리팩토링 할 수 있다!
    // 마우스 우클릭 => 리팩토링!!
    memberRepository.findByName(member.getName())
    .ifPresent(m -> {
           throw new IllegalStateException("이미 존재하는 회원입니다.");
         });
     */


    validateDuplicateMember(member); // 중복 회원 검증 => 비즈니스 로직 추가!
    memberRepository.save(member); // 검증 통과하면 memberRepository에 회원 저장
    return member.getId(); // 가입한 회원의 ID 반환!
  }

  // 중복 회원 검증 코드!!! 
  private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName())
        .ifPresent(m -> {
          throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
  }

  /**
   * 전체 회원 조회
   */
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }
}