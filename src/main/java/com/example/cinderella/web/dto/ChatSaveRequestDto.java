package com.example.cinderella.web.dto;

import com.example.cinderella.domain.chat.Chat;
import lombok.Builder;

public class ChatSaveRequestDto {
    private String host;
    private String start;
    private String dest;
    private int time;
    private int num_of_people;
    public ChatSaveRequestDto(String host, String start, String dest, int time, int num_of_people) {
        this.host = host;
        this.start = start;
        this.dest = dest;
        this.time = time;
        this.num_of_people = num_of_people;
    }
    public Chat toEntity() {
        return Chat.builder()
                .host(host)
                .start(start)
                .dest(dest)
                .time(time)
                .num_of_people(num_of_people)
                .build();
    }
}
