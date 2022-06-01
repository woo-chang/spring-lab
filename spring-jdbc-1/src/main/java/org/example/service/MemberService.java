package org.example.service;

import java.util.List;
import java.util.stream.Collectors;
import org.example.dao.hardcoding.MemberDao;
import org.example.domain.Member;
import org.example.dto.request.MemberRequest;
import org.example.dto.response.MemberResponse;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  private final MemberDao memberDao;

  public MemberService(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  public MemberResponse createMember(String firstName, String lastName) {
    Member member = memberDao.createMember(firstName, lastName);
    return member.toResponse();
  }

  public MemberResponse updateMember(Integer id, MemberRequest memberRequest) {
    Member member = memberDao.updateMember(id, memberRequest);
    return member.toResponse();
  }

  public List<MemberResponse> findAllMembers() {
    return memberDao.findAllMembers().stream().map(MemberResponse::new)
        .collect(Collectors.toList());
  }

  public MemberResponse deleteMember(Integer id) {
    Member member = memberDao.deleteMember(id);
    return member.toResponse();
  }

}
