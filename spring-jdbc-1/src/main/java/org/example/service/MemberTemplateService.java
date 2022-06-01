package org.example.service;

import java.util.List;
import java.util.stream.Collectors;
import org.example.dao.template.MemberTemplateDao;
import org.example.domain.Member;
import org.example.dto.request.MemberRequest;
import org.example.dto.response.MemberResponse;
import org.springframework.stereotype.Service;

@Service
public class MemberTemplateService {

  private final MemberTemplateDao memberTemplateDao;

  public MemberTemplateService(MemberTemplateDao memberTemplateDao) {
    this.memberTemplateDao = memberTemplateDao;
  }

  public MemberResponse createMember(String firstName, String lastName) {
    Member member = memberTemplateDao.createMember(firstName, lastName);
    return member.toResponse();
  }

  public MemberResponse updateMember(Integer id, MemberRequest memberRequest) {
    Member member = memberTemplateDao.updateMember(id, memberRequest);
    return member.toResponse();
  }

  public List<MemberResponse> findAllMembers() {
    List<Member> members = memberTemplateDao.findAllMembers();
    return members.stream().map(MemberResponse::new).collect(Collectors.toList());
  }

  public Integer deleteMember(Integer id) {
    return memberTemplateDao.deleteMember(id);
  }

}
