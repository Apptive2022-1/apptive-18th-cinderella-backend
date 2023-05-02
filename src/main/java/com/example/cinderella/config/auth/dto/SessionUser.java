package com.example.cinderella.config.auth.dto;

import com.example.cinderella.domain.user.Users;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(Users users) {
        this.name = users.getName();
        this.email = users.getEmail();
    }
}
