package com.example.cinderella.web;

import com.example.cinderella.web.dto.UsersResponseDto;
import com.example.cinderella.service.ChatroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
@RequiredArgsConstructor
@RestController
public class ChatroomController {
    private final ChatroomService chatroomService;

    @GetMapping("/chatroom/{chatid}")
    public List<UsersResponseDto> findChatroom(@PathVariable Long chatid, Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        chatroomService.updateNumofpeople(chatid);
        chatroomService.updateChatid(oAuth2User.getAttribute("email"), chatid);
        List<UsersResponseDto> users = chatroomService.findAllByChatid(chatid);
        return users;
    }
}
