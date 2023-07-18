package com.example.cinderella.web;

import com.example.cinderella.config.auth.dto.SessionUser;
import com.example.cinderella.web.dto.UsersResponseDto;
import com.example.cinderella.service.ChatroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
@RequiredArgsConstructor
@RestController
public class ChatroomController {
    private final ChatroomService chatroomService;

    /**
     * 방 참가기능 : 방에 참여가 완료되면 채팅방id, dest를 전달받아 Chat에 저장하여 return해줍니다
     */
    @GetMapping("/chatroom/{chatid}/{dest}")
    public List<UsersResponseDto> findChatroom(@PathVariable Long chatid,
                                               @PathVariable String dest,
                                               Authentication authentication){
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        chatroomService.updateNumofpeople(chatid);
        chatroomService.updateChatid(email, chatid);
        chatroomService.updateDest(email, chatid, dest);
        List<UsersResponseDto> users = chatroomService.findAllByChatid(chatid);
        return users;
    }
}
