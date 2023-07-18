package com.example.cinderella.config.auth;

import com.example.cinderella.domain.user.Users;
import com.example.cinderella.domain.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UsersRepository usersRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 소셜 로그인된 사용자 정보를 불러옴
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("SuccessHandler : oAuth2User = " + oAuth2User);
        String email = oAuth2User.getAttribute("email");
        response.sendRedirect("/check");
    }
}
