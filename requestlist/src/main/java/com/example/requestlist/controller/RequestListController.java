package com.example.requestlist.controller;

import com.example.requestlist.dto.RequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class RequestListController {

  @GetMapping("/lists")
  public String lists(@RequestBody RequestDto requestDto) {
    StringBuilder sb = new StringBuilder();
    for(Integer id : requestDto.getSelectIds()) {
      sb.append(id).append(",");
    }
    return sb.toString();
  }
}
