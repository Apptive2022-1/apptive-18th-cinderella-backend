package com.example.cinderella.web.dto;

import com.example.cinderella.domain.user.Users;
import lombok.Getter;

@Getter
public class UsersResponseDto {

    private Long id;
    private String name;
    private Long chatid;

    public UsersResponseDto(Users entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.chatid = entity.getChatid();
    }
}
