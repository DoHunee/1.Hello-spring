package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

public class JpaMemberRepository implements MemberRepository {

  private final EntityManager em;

   // 엔티티 매니저 객체
   // build.gradle에서 스프링 데이터 JPA 추가하면서 스프링 부트가 자동으로 EntityManager를 생성해줌
   // em이 EntityManager다!
  public JpaMemberRepository(EntityManager em) {
    this.em = em;
  }

  
  // 멤버 저장: EntityManager를 사용하여 멤버를 영속성 컨텍스트에 저장
  public Member save(Member member) {
    em.persist(member);
    return member;
  }


  // ID로 멤버 찾기: ID를 사용하여 멤버를 조회
  public Optional<Member> findById(Long id) {
    Member member = em.find(Member.class, id);
    return Optional.ofNullable(member);
  }

  // 이름으로 멤버 찾기: 이름을 기준으로 멤버 조회
  public Optional<Member> findByName(String name) {
    List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
    .setParameter("name", name)
    .getResultList();
    
    return result.stream().findAny();
    }
  
  // 모든 멤버 조회: JPQL을 사용하여 모든 멤버를 조회
  public List<Member> findAll() {
    return em.createQuery("select m from Member m", Member.class) //jpql 쿼리문
        .getResultList();
  } 

  

}