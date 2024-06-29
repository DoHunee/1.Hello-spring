package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.*;


//회원 레포지토리 구현체

public class MemoryMemberRepository implements MemberRepository {

  // 회원 저장소로 사용할 Map. Key는 회원 ID, Value는 회원 객체
  private static Map<Long, Member> store = new HashMap<>();
  private static long sequence = 0L;


  // Member라는 회원 객체에 시퀀스 값 증가시켜서 store에 저장하는 메서드
  @Override
  public Member save(Member member) {
    member.setId(++sequence); // 회원 ID를 시퀀스를 이용해 설정
    store.put(member.getId(), member); // Map에 회원 객체를 저장
    return member;
  }

  //ID를 통해 회원을 조회하는 메서드
  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id)); // ID로 회원을 조회하여 Optional로 반환
  }

  /**
   * 모든 회원을 조회하는 메서드
   * @return 모든 회원 객체를 포함한 List
   */
  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values()); // 저장소에 있는 모든 회원 객체를 List로 반환
  }

  /**
   * 이름을 통해 회원을 조회하는 메서드
   * @param name 조회할 회원 이름
   * @return 조회된 회원 객체를 포함한 Optional 객체
   */
  @Override
  public Optional<Member> findByName(String name) {
    // 스트림을 사용하여 저장소의 모든 회원 중 이름이 일치하는 회원을 찾음
    return store.values().stream()
        .filter(member -> member.getName().equals(name))
        .findAny(); // 첫 번째로 발견된 일치하는 회원을 Optional로 반환
  }


  // 저장소를 비우는 메서드. 주로 테스트에서 사용
  public void clearStore() {
    store.clear(); // 저장소를 초기화
  }
}