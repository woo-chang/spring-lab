package org.example.dao.template;

import java.util.List;
import javax.sql.DataSource;
import org.example.domain.Member;
import org.example.dto.request.MemberRequest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class MemberTemplateDao {

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleJdbcInsert;
  // 조회 쿼리 메서드마다 중복을 줄일 수 있다.
  private final RowMapper<Member> rowMapper = ((rs, rowNum) ->
      new Member(
          rs.getInt("id"),
          rs.getString("first_name"),
          rs.getString("last_name")
      )
  );

  public MemberTemplateDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
    this.jdbcTemplate = jdbcTemplate;
    this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
        .withTableName("MEMBERS")
        .usingGeneratedKeyColumns("id");
  }

  /**
   * CREATE
   */
  public Member createMember(String firstName, String lastName) {
    // 해당하는 키를 얻을 수 없는 단점
    // String sql = "insert into members(first_name, last_name) values(?, ?)";
    // jdbcTemplate.update(sql, firstName, lastName);
    Integer id = -1;
    try {
      SqlParameterSource params = new MapSqlParameterSource()
          .addValue("first_name", firstName)
          .addValue("last_name", lastName);
      id = simpleJdbcInsert.executeAndReturnKey(params).intValue();
    } catch (DuplicateKeyException e) {
      e.printStackTrace();
    }
    return new Member(id, firstName, lastName);
  }

  /**
   * UPDATE
   */
  public Member updateMember(Integer id, MemberRequest memberRequest) {
    String sql = "update members set first_name=?, last_name=? where id = ?";
    jdbcTemplate.update(sql, memberRequest.getFirstName(), memberRequest.getLastName(), id);
    return new Member(id, memberRequest.getFirstName(), memberRequest.getLastName());
  }

  /**
   * READ
   */
  public List<Member> findAllMembers() {
    String sql = "select * from members";
    return jdbcTemplate.query(sql, rowMapper);
  }

  /**
   * DELETE
   */
  public Integer deleteMember(Integer id) {
    String sql = "delete from members where id = ?";
    jdbcTemplate.update(sql, id);
    return id;
  }
}
