package com.example.cinderella.web;

import com.example.cinderella.config.auth.dto.SessionUser;
import com.example.cinderella.domain.user.Gender;
import com.example.cinderella.domain.user.Users;
import com.example.cinderella.domain.user.UsersRepository;
import com.example.cinderella.service.ChatService;
import com.example.cinderella.web.dto.SignUpRequestDto;
import com.example.cinderella.web.dto.SignedUpCheckDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.naming.factory.SendMailFactory;
import org.h2.engine.Session;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final ChatService chatService;

    /**
     * 회원가입 유무 확인 : 성별 유무로 회원가입 여부를 확인하고, 회원가입이 되어있다면 true, 아니면 false
     */
    @GetMapping("/check")
    public String check(Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String state = chatService.findGender(email);
        return state;
    }
//    public SignedUpCheckDto check(HttpSession session,
//                                        Authentication authentication) {
//        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//        String email = oAuth2User.getAttribute("email");
//        String state = chatService.findGender(email);
//        String sessionId = session.getId();
//
//        return SignedUpCheckDto.builder()
//                .status(state)
//                .sessionID(sessionId)
//                .build();
//    }

    /**
     * 회원 가입 : name, gender를 받아서 update해줌
     */
    @PutMapping("/signup")
    public void signUp(@RequestBody SignUpRequestDto signUpRequestDto,
                       Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("oAuth2User = " + oAuth2User);
        String email = oAuth2User.getAttribute("email");
        chatService.signUp(email, signUpRequestDto);
    }
}
