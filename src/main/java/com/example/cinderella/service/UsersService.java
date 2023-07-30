package com.example.cinderella.service;

import com.example.cinderella.domain.user.Users;
import com.example.cinderella.domain.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;


    /**
     * 현재 로그인한 멤버의 이메일
     */
    public String findEmail(Authentication authentication){
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        return oAuth2User.getAttribute("email");
    }

    /**
     * 방에 참여중인지 확인 : true라면 참여중, false라면 방에 참여하지 않은상태
     */
    public boolean isParticipate(Authentication authentication){
        String userEmail = findEmail(authentication);
        Users users = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 정보가 없습니다. email=" + userEmail));
        return users.getParticipating();
    }

    /**
     * 블랙리스트에 추가
     */
    @Transactional
    public void goToJail(String userEmail, String blockEmail){
        Users users = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 정보가 없습니다. email=" + userEmail));
        Set<String> before = users.getBlockList();
        String printBefore = before.stream()
                .collect(Collectors.joining(""));
        System.out.println("추가 전 차단리스트 -> "+printBefore);

        //추가
        users.addBlockList(blockEmail);

        Set<String> after = users.getBlockList();
        String printAfter = after.stream()
                .collect(Collectors.joining(" "));
        System.out.println("추가 후 차단리스트 -> "+printAfter);

        // 유저의 blockList를 업데이트 후, users를 다시 repository에 저장해줘야함
        usersRepository.save(users);

    }

    /**
     * 블랙리스트에서 제거
     */
    @Transactional
    public void escapePrison(String userEmail, String blockEmail){
        Users users = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 정보가 없습니다. email=" + userEmail));
        Set<String> before = users.getBlockList();
        String printBefore = before.stream()
                .collect(Collectors.joining(""));
        System.out.println("삭제 전 차단리스트 -> "+printBefore);

        //삭제
        users.removeBlockList(blockEmail);

        Set<String> after = users.getBlockList();
        String printAfter = after.stream()
                .collect(Collectors.joining(" "));
        System.out.println("삭제 후 차단리스트 -> "+printAfter);

        // 유저의 blockList를 업데이트 후, users를 다시 repository에 저장해줘야함
        usersRepository.save(users);

    }
}
