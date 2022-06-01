package org.example.controller;

import java.util.List;
import org.example.dto.request.MemberRequest;
import org.example.dto.response.MemberResponse;
import org.example.service.MemberTemplateService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/template/members")
public class MemberTemplateController {

  private final MemberTemplateService memberTemplateService;

  public MemberTemplateController(MemberTemplateService memberTemplateService) {
    this.memberTemplateService = memberTemplateService;
  }

  @PostMapping
  public MemberResponse createMember(
      @RequestParam String firstName,
      @RequestParam String lastName) {
    return memberTemplateService.createMember(firstName, lastName);
  }

  @PatchMapping("/{id}")
  public MemberResponse updateMember(
      @PathVariable Integer id,
      @RequestBody MemberRequest memberRequest
  ) {
    return memberTemplateService.updateMember(id, memberRequest);
  }

  @GetMapping
  public List<MemberResponse> findAllMembers() {
    return memberTemplateService.findAllMembers();
  }

  @DeleteMapping("/{id}")
  public Integer deleteMemeber(
    @PathVariable Integer id
  ) {
    return memberTemplateService.deleteMember(id);
  }
}
