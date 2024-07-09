package hello.hellospring;

// import hello.hellospring.repository.JdbcMemberRepository;
// import hello.hellospring.repository.JdbcTemplateMemberRepository;
// import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
// import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
// import jakarta.persistence.EntityManager;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import javax.sql.DataSource;

@Configuration
public class SpringConfig {

  // private final DataSource dataSource;

  // public SpringConfig(DataSource dataSource) {
  // this.dataSource = dataSource;
  // }

  // private EntityManager em;

  // public SpringConfig(EntityManager em) {
  // this.em = em;
  // }

  private final MemberRepository memberRepository;

  public SpringConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository);
  }



  /* 
  @Bean
  public MemberRepository memberRepository() {

    // 이런식으로 레포지토리를 주입받아 조립해서 사용할 수 있다!!
     

    // return new MemoryMemberRepository();
    // return new JdbcMemberRepository(dataSource);
    // return new JdbcTemplateMemberRepository(dataSource); //
    // MemberServiceIntegrationTest에서 Test하면 된다!!
    // return new JpaMemberRepository(em); // MemberServiceIntegrationTest에서 Test하면 된다!!


  }
  */

}