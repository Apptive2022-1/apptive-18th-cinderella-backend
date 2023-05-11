package com.example.cinderella.web;

import com.example.cinderella.service.ChatService;
import com.example.cinderella.web.dto.ChatResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/chatlist/{start}")
    public List<ChatResponseDto> findChatlist(@PathVariable String start) {
        List<ChatResponseDto> chat = chatService.findAllByStart(start);
        return chat;
    }

    /**
     * 방 생성 : 호스트, 출발지, 목적지, 출발시간을 받아서 db에 저장, num_of_people은 1로 고정
     */
    @GetMapping("/createchat")
    public void createChat(@RequestParam("host") String host,
                           @RequestParam("start") String start,
                           @RequestParam("dest") String dest,
                           @RequestParam("time") int time){
        chatService.saveChat(host, start, dest, time);
    }
}
