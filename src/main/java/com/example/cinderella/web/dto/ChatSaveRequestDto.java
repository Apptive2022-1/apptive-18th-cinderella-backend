package com.example.cinderella.web.dto;

import com.example.cinderella.domain.chat.Chat;
import lombok.Builder;

public class ChatSaveRequestDto {
    private String host;
    private String start;
    private String dest;
    private int time;
    private int num_of_people;
    public ChatSaveRequestDto(String host, String start, String dest, int time) {
        this.host = host;
        this.start = start;
        this.dest = dest;
        this.time = time;
        this.num_of_people = 1;
    }

    /**
     * 각 변수를 저장한 entity를 return
     */
    public Chat toEntity() {
        Chat chat = Chat.builder()
                .host(host)
                .start(start)
                .dest(dest)
                .time(time)
                .num_of_people(num_of_people)
                .build();
        return chat;
    }
}
