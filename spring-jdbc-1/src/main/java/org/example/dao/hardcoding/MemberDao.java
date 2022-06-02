package org.example.dao.hardcoding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.example.domain.Member;
import org.example.dto.request.MemberRequest;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

  /**
   * DB 연결을 위한 커넥션을 얻는 과정 1. 해당 드라이버 클래스 탐색 2. 드라이버 매니저로부터 커넥션 획득 후 반환
   */
  public Connection connection() {
    Connection connection = null;
    String server = "localhost:3306";
    String database = "jdbc_test";
    String option = "?serverTimezone=UTC&characterEncoding=UTF-8";
    String username = "####";
    String password = "####";

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    try {
      connection = DriverManager.getConnection(
          "jdbc:mysql://" + server + "/" + database + option,username,password);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return connection;
  }

  /**
   * 연결된 커넥션을 종료하는 함수
   */
  public void closeConnection(Connection connection) {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * 사용한 자원들을 종료해준다.
   *
   * @param preparedStatement -> sql 구문을 실행시키는 기능을 가진 자원
   * @param resultSet         -> sql 구문의 결과를 저장하는 기능을 가진 자원
   * @throws SQLException
   */
  protected void disconnect(PreparedStatement preparedStatement, ResultSet resultSet)
      throws SQLException {
    if (!Objects.isNull(preparedStatement)) {
      preparedStatement.close();
    }
    if (!Objects.isNull(resultSet)) {
      resultSet.close();
    }
  }

  /**
   * CREATE
   */
  public Member createMember(String firstName, String lastName) {
    Connection con = connection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Member member = null;
    try {
      String sql = "insert into members(first_name, last_name) values(?, ?)";
      ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, firstName);
      ps.setString(2, lastName);
      ps.executeUpdate();
      rs = ps.getGeneratedKeys();
      if (rs.next()) {
        member = new Member(rs.getInt(1), firstName, lastName);
      }
      closeConnection(con);
      disconnect(ps, rs);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return member;
  }

  /**
   * UPDATE
   */
  public Member updateMember(Integer id, MemberRequest memberRequest) {
    Connection con = connection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Member member = null;
    try {
      String sql = "update members set first_name = ?, last_name = ? where id = ?";
      ps = con.prepareStatement(sql);
      ps.setString(1, memberRequest.getFirstName());
      ps.setString(2, memberRequest.getLastName());
      ps.setInt(3, id);
      ps.executeUpdate();
      member = new Member(id, memberRequest.getFirstName(), memberRequest.getLastName());
      closeConnection(con);
      disconnect(ps, rs);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return member;
  }

  /**
   * READ
   */
  public List<Member> findAllMembers() {
    Connection con = connection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Member> members = new ArrayList<>();
    try {
      String sql = "select * from members";
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        members.add(new Member(rs.getInt(1), rs.getString(2), rs.getString(3)));
      }
      closeConnection(con);
      disconnect(ps, rs);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return members;
  }

  /**
   * DELETE
   */
  public Member deleteMember(Integer id) {
    Connection con = connection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Member member = null;
    try {
      String sql = "delete from members where id = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, id);
      ps.executeUpdate();
      member = new Member(id, "", "");
      closeConnection(con);
      disconnect(ps, rs);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return member;
  }
}
