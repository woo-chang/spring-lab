package com.example.requestlist.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class RequestDto {

  private Long id;
  private List<Integer> selectIds;
}
