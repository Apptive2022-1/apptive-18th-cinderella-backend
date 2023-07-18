package com.example.cinderella.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class SignedUpCheckDto {
    private final String status;
    private final String sessionID;
}
