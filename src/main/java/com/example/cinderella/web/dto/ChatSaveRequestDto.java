package com.example.cinderella.web.dto;

import com.example.cinderella.domain.chat.Chat;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChatSaveRequestDto {
    private String host;
    private String start;
    private String dest;
    private int time;
    private int num_of_people;
    private List<String> destList = new ArrayList<>();

    public ChatSaveRequestDto(String host, String start, String dest, List destList, int time) {
        this.host = host;
        this.start = start;
        this.dest = dest;
        this.destList.add(dest);
        this.time = time;
        this.num_of_people = 1;
    }

    /**
     * 각 변수를 저장한 entity를 return
     */
    public Chat toEntity() {
        return Chat.builder()
                .host(host)
                .start(start)
                .time(time)
                .dest(destList)
                .num_of_people(num_of_people)
                .build();
    }
}
