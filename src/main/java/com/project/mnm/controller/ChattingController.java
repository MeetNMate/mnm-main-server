package com.project.mnm.controller;

import com.project.mnm.domain.Chatting;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChattingController {
    @MessageMapping("/receive")
    @SendTo("/send")
    public Chatting chattingHandler(Chatting chatting) {
        return chatting;
    }
}