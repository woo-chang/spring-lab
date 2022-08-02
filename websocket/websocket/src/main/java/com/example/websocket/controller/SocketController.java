package com.example.websocket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SocketController {

  private final SimpMessagingTemplate webSocket;

  /**
   * @SendTo 어노테이션을 사용하는 방식
   * 해당 topics를 수신하는 Client Websocket에게 메시지 전달
   * 리턴 타입을 정의해야하며 반환값 리턴을 통해 Client에게 전달
   */
  @MessageMapping("/vote1")
  @SendTo("/topics/vote1")
  public String SendToMessage() throws Exception {

    return "투표 인원 증가1";
  }

  /**
   * SimpMessagingTemplate를 사용해서 응답값을 전달하는 방법
   * 리턴값을 void로 처리
   */
  @MessageMapping("/vote2")
  public void SendTemplateMessage() {
    webSocket.convertAndSend("/topics/vote2" , "투표 인원 증가2");
  }

  /**
   * 단순 호출로 메시지를 보내는 방법
   */
  @GetMapping(value="/vote3")
  public void SendAPI() {
    webSocket.convertAndSend("/topics/vote3" , "투표 인원 증가3");
  }

}
