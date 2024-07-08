package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import javax.sql.DataSource;
// import java.sql.ResultSet;
// import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {
  
  private final JdbcTemplate jdbcTemplate;

    // JdbcTemplate을 사용하는 레포지토리 생성자, 데이터 소스 주입
  public JdbcTemplateMemberRepository(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }


  // 멤버 저장: SimpleJdbcInsert를 사용해 DB에 멤버를 저장하고 생성된 키를 반환
  @Override
  public Member save(Member member) {
    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
    
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("name", member.getName());
    
    Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    member.setId(key.longValue());
    
    return member;
  }
  

  // ID로 멤버 조회: ID를 사용하여 데이터베이스에서 멤버를 검색하고, 있으면 반환
  @Override
  public Optional<Member> findById(Long id) {
 List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);  //memberRowMapper()는 아래에 정의되어 있다.
 return result.stream().findAny(); 
 }

 
  // 이름으로 멤버 조회: 주어진 이름으로 멤버를 검색하고, 있으면 반환
  @Override
  public Optional<Member> findByName(String name) {
  List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
  return result.stream().findAny();
  }
 
      
  // 모든 멤버 조회: 모든 멤버를 조회하여 리스트로 반환
 @Override
  public List<Member> findAll() {
    return jdbcTemplate.query("select * from member", memberRowMapper());
  }


 // 멤버 RowMapper: ResultSet에서 데이터를 읽어 Member 객체를 생성하고 반환
  private RowMapper<Member> memberRowMapper() {
    return (rs, rowNum) -> {
      Member member = new Member();
      member.setId(rs.getLong("id"));
      member.setName(rs.getString("name"));
      return member;
    };
  }



}