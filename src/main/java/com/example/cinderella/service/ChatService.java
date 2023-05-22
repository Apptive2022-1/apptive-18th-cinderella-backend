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
        List<Chat> entity = chatRepository.findAllByStart(start);
        return entity.stream()
                .map(ChatResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 방 생성 : host,start,dest,time을 넘겨받아 entity로 바꿔서 저장
     */
    @Transactional
    public void saveChat(ChatSaveRequestDto requestDto){
        chatRepository.save(requestDto.toEntity());
    }

}
