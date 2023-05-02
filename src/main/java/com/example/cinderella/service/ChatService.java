package com.example.cinderella.service;

import com.example.cinderella.domain.chat.Chat;
import com.example.cinderella.domain.chat.ChatRepository;
import com.example.cinderella.web.dto.ChatResponseDto;
import com.example.cinderella.web.dto.ChatSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRepository chatRepository;

    @Transactional(readOnly = true)
    public List<ChatResponseDto> findAllByStart(String start) {
        ChatSaveRequestDto requestDto = new ChatSaveRequestDto("천주희", "test", "test1", 500, 2);
        chatRepository.save(requestDto.toEntity());
        requestDto = new ChatSaveRequestDto("홍길동", "test", "test2", 400, 3);
        chatRepository.save(requestDto.toEntity());
        requestDto = new ChatSaveRequestDto("김철수", "test", "test3", 550, 1);
        chatRepository.save(requestDto.toEntity());

        List<Chat> entity = chatRepository.findAllByStart(start);
        return entity.stream()
                .map(ChatResponseDto::new)
                .collect(Collectors.toList());
    }

}
