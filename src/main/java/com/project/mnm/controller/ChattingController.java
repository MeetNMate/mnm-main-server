package com.project.mnm.controller;

import com.project.mnm.config.JwtTokenProvider;
import com.project.mnm.domain.Chatting;
import com.project.mnm.domain.Response;
import com.project.mnm.dto.ChattingRoomInsertDto;
import com.project.mnm.service.ChattingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChattingController {
    @Autowired
    private ChattingService chattingService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @MessageMapping("/receive")
    @SendTo("/send")
    public Chatting chattingHandler(Chatting chatting) {
        return chattingService.chattingHandler(chatting);
    }

    @PostMapping("/chat")
    public Chatting makeChattingRoom(@RequestBody ChattingRoomInsertDto chattingRoomInsertDto) {
        return chattingHandler(chattingService.makeChattingRoom(chattingRoomInsertDto));
    }

    @GetMapping("/user/chattingRoom")
    public Response getChattingRoomList(@RequestHeader(value = "X-AUTH-TOKEN") String token) {
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setMessage("채팅방 조회를 성공적으로 완료했습니다.");
            response.setData(chattingService.getChattingRoomList(jwtTokenProvider.getUserPk(token)));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("채팅방 조회를 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @GetMapping("/user/chatting/{cid}")
    public Response getChattingList(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                    @PathVariable("cid") Long cid) {
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setMessage("채팅방 조회를 성공적으로 완료했습니다.");
            response.setData(chattingService.getChattingList(jwtTokenProvider.getUserPk(token), cid));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("채팅방 조회를 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }
}