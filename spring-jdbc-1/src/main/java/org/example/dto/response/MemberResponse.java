package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.domain.Member;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {

  private int id;
  private String firstName;
  private String lastName;

  public MemberResponse(Member member) {
    this.id = member.getId();
    this.firstName = member.getFirstName();
    this.lastName = member.getLastName();
  }
}
