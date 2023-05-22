package com.example.cinderella.web;

import com.example.cinderella.service.ChatService;
import com.example.cinderella.web.dto.ChatResponseDto;
import com.example.cinderella.web.dto.ChatSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/chatroom")
    public void createChat(@RequestBody ChatSaveRequestDto requestDto){
        chatService.saveChat(requestDto);
    }
}
