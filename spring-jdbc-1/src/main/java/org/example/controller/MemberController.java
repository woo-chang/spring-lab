package org.example.controller;

import java.util.List;
import org.example.dto.request.MemberRequest;
import org.example.dto.response.MemberResponse;
import org.example.service.MemberService;
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
@RequestMapping("/members")
public class MemberController {

  private final MemberService memberService;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @PostMapping
  public MemberResponse createMemeber(
    @RequestParam String firstName,
    @RequestParam String lastName
  ) {
    return memberService.createMember(firstName, lastName);
  }

  @PatchMapping("/{id}")
  public MemberResponse updateMember(
      @PathVariable Integer id,
      @RequestBody MemberRequest memberRequest
  ) {
    return memberService.updateMember(id, memberRequest);
  }

  @GetMapping
  public List<MemberResponse> findAllMembers() {
    return memberService.findAllMembers();
  }

  @DeleteMapping("/{id}")
  public MemberResponse deleteMember(
      @PathVariable Integer id
  ) {
    return memberService.deleteMember(id);
  }
}
