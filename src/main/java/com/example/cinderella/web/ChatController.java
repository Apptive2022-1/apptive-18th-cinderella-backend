package com.example.cinderella.web;

import com.example.cinderella.service.ChatService;
import com.example.cinderella.service.UsersService;
import com.example.cinderella.web.dto.ChatResponseDto;
import com.example.cinderella.web.dto.ChatSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatController {
    private final ChatService chatService;

    /**
     * 방 리스트 : start에 해당하는 방 리스트를 생성
     */
    @GetMapping("/chatlist/{start}")
    public List<ChatResponseDto> findChatlist(@PathVariable String start) {
        List<ChatResponseDto> chat = chatService.findAllByStart(start);
        return chat;
    }

    /**
     * 방 생성 : 호스트, 출발지, 목적지, 출발시간을 받아서 db에 저장, num_of_people은 1로 고정
     */
    @PostMapping("/chatroom")
    public void createChat(@RequestBody ChatSaveRequestDto requestDto, Authentication authentication){
        List<String> destList = new ArrayList<>();
        destList.add(requestDto.getDest());
        chatService.saveChat(requestDto, authentication);
    }
}
