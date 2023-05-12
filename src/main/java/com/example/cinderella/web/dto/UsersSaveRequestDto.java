package com.example.cinderella.web.dto;

import com.example.cinderella.domain.chat.Chat;
import com.example.cinderella.domain.user.Role;
import com.example.cinderella.domain.user.Users;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UsersSaveRequestDto {

    private String name;

    private String email;

    private Role role;

    public UsersSaveRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
        this.role = Role.USER;
    }
    public Users toEntity() {
        return Users.builder()
                .name(name)
                .email(email)
                .role(role)
                .build();
    }
}
