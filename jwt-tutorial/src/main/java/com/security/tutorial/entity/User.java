package com.security.tutorial.entity;

import java.util.Set;
import lombok.*;
import javax.persistence.*;

@Entity // 데이터베이스 테이블과 1대1 매칭되는 객체
@Table(name = "user") // 테이블명을 user로 지정
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(name = "username", length = 50, unique = true)
  private String username;

  @Column(name = "password", length = 100)
  private String password;

  @Column(name = "nickname", length = 50)
  private String nickname;

  @Column(name = "activated")
  private boolean activated;

  @ManyToMany
  @JoinTable(
      name = "user_authority",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
  private Set<Authority> authorities;
}