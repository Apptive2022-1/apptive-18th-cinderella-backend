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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final ChatService chatService;

    /**
     * 회원가입 유무 확인 : 성별 유무로 회원가입 여부를 확인하고, 회원가입이 되어있다면 true, 아니면 false
     */
    @GetMapping("/check")
    public String check(Authentication authentication,
                        HttpServletRequest request) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String state = chatService.findGender(email);
//        HttpSession session = request.getSession();
//        Cookie sessionCookie = Arrays.stream(request.getCookies())
//                .filter(cookie -> cookie.getName().equals("SESSION"))
//                .findFirst()
//                .orElse(null);
//        System.out.println("sessionCookie = " + sessionCookie);
//        String cookieValue = sessionCookie.getValue();
//        System.out.println("cookieValue = " + cookieValue);
//        System.out.println("HttpSession.getId() = " + session.getId());
        System.out.println("회원가입 상태 : " + state);
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
        String email = oAuth2User.getAttribute("email");
        chatService.signUp(email, signUpRequestDto);
    }

    /**
     * 로그 아웃 : 세션에서 정보 삭제
     */
    @PostMapping("/memberlogout")
    public ResponseEntity<String> logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        System.out.println("LogOut Success");
        return new ResponseEntity<>("LogOut Success", HttpStatus.OK);
    }
}
