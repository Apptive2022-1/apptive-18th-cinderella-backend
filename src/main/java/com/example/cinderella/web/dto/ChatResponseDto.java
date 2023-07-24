package com.example.cinderella.web.dto;

import com.example.cinderella.domain.chat.Chat;
import lombok.Getter;

import java.util.List;

@Getter
public class ChatResponseDto {

    private Long id;
    private String host;
    private String start;
    //    private String dest;
    private List<String> dest;
    private String time;
    private int num_of_people;
    private String openChatLink;
    private String chatName;


    public ChatResponseDto(Chat entity) {
        this.id = entity.getId();
        this.host = entity.getHost();
        this.start = entity.getStart();
        this.dest = entity.getDest();
        this.num_of_people = entity.getNum_of_people();
        this.time = entity.getCalcTime();
        this.openChatLink = entity.getOpenChatLink();
        this.chatName = entity.getChatName();
    }
}
