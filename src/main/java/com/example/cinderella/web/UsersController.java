package com.example.cinderella.web;

import com.example.cinderella.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    /**
     * 차단목록에 추가 및 제거
     */
    @PutMapping("/addblocklist")
    public void addBlockList(@RequestBody HashMap data, Authentication authentication){
        String blockEmail = (String) data.get("blockEmail");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String userEmail = oAuth2User.getAttribute("email");
        usersService.goToJail(userEmail, blockEmail);
    }

    @DeleteMapping("/removeblocklist")
    public void deleteBlockList(@RequestBody HashMap data, Authentication authentication) {
        String blockEmail = (String) data.get("blockEmail");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String userEmail = oAuth2User.getAttribute("email");
        usersService.escapePrison(userEmail, blockEmail);
    }
}
