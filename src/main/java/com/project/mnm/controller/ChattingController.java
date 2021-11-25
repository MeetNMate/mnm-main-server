package com.project.mnm.controller;

import com.project.mnm.config.JwtTokenProvider;
import com.project.mnm.domain.Chatting;
import com.project.mnm.domain.Response;
import com.project.mnm.dto.ChattingRoomInsertDto;
import com.project.mnm.dto.SocketSessionInsertDto;
import com.project.mnm.service.ChattingService;
import com.project.mnm.service.SocketSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChattingController {
    @Autowired
    private ChattingService chattingService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private SocketSessionService socketSessionService;

    @MessageMapping("/receive/{cid}")
    @SendTo("/send/{cid}")
    public Chatting chattingHandler(@DestinationVariable Long cid,
                                    Chatting chatting) {
        // 나중에 Dto 삭제해도 됨
        return chattingService.chattingHandler(cid, chatting);
    }

    @MessageMapping("/firstreceive")
    public void firstHandler(SocketSessionInsertDto socketSessionInsertDto) {
        socketSessionService.addSession(socketSessionInsertDto.getCid(),
                socketSessionInsertDto.getUid(), socketSessionInsertDto.getSid());
    }

    @PostMapping("/user/chattingRoom/make")
    public Chatting makeChattingRoom(@RequestBody ChattingRoomInsertDto chattingRoomInsertDto) {
        return chattingService.makeChattingRoom(chattingRoomInsertDto);
    }

    @GetMapping("/chattingRoom/exist")
    public Boolean isExisted(@RequestParam("senderUid") Long senderUid,
                             @RequestParam("receiverUid") Long receiverUid) {
        return chattingService.isExisted(senderUid, receiverUid);
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

    @GetMapping("/user/chattingRoom/{cid}/latest")
    public Response getChattingRoomLatest(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                          @PathVariable("cid") Long cid) {
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setMessage("채팅방 최신 정보 조회를 성공적으로 완료했습니다.");
            response.setData(chattingService.getChattingRoomLatest(jwtTokenProvider.getUserPk(token), cid));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("채팅방 최신 정보 조회를 하는 도중 오류가 발생했습니다.");
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

    @PostMapping("/user/chatting/{cid}/request")
    public Response sendRequest(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                @PathVariable("cid") Long cid) {
        Response response = new Response();

        try {
            Chatting chatting = chattingService.sendRequest(jwtTokenProvider.getUserPk(token), cid);
            simpMessagingTemplate.convertAndSend("/send/"+cid, chatting);
            response.setResponse("success");
            response.setMessage("메이트 요청을 성공적으로 완료했습니다.");
            response.setData(chatting);
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("메이트 요청을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @PostMapping("/user/chatting/{cid}/accept")
    public Response acceptRequest(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                  @PathVariable("cid") Long cid) {
        Response response = new Response();

        try {
            Chatting chatting = chattingService.acceptRequest(jwtTokenProvider.getUserPk(token), cid);
            simpMessagingTemplate.convertAndSend("/send/"+cid, chatting);
            response.setResponse("success");
            response.setMessage("메이트 요청 수락을 성공적으로 완료했습니다.");
            response.setData(chatting);
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("메이트 요청 수락을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @PostMapping("/user/chatting/{cid}/decline")
    public Response declineRequest(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                  @PathVariable("cid") Long cid) {
        Response response = new Response();

        try {
            Chatting chatting = chattingService.declineRequest(jwtTokenProvider.getUserPk(token), cid);
            simpMessagingTemplate.convertAndSend("/send/"+cid, chatting);
            response.setResponse("success");
            response.setMessage("메이트 요청 거절을 성공적으로 완료했습니다.");
            response.setData(chatting);
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("메이트 요청 거절을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }
}