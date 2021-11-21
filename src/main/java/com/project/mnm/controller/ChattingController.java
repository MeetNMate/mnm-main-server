package com.project.mnm.controller;

import com.project.mnm.domain.Chatting;
import com.project.mnm.service.ChattingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChattingController {
    @Autowired
    private ChattingService chattingService;

    @MessageMapping("/receive")
    @SendTo("/send")
    public Chatting chattingHandler(@DestinationVariable("cid") Long cid,
                                    Chatting chatting) {
        System.out.println(cid);
        return chattingService.chattingHandler(chatting);
    }
}