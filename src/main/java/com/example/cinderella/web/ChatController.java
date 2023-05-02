package com.example.cinderella.web;

import com.example.cinderella.service.ChatService;
import com.example.cinderella.web.dto.ChatResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
