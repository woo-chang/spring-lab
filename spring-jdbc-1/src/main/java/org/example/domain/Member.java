package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.dto.response.MemberResponse;

@Getter
@AllArgsConstructor
public class Member {

  private int id;
  private String firstName;
  private String lastName;

  public MemberResponse toResponse() {
    return MemberResponse.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .build();
  }
}
