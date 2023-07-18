package com.example.cinderella.service;

import com.example.cinderella.domain.chat.Chat;
import com.example.cinderella.domain.chat.ChatRepository;
import com.example.cinderella.domain.user.Gender;
import com.example.cinderella.domain.user.Role;
import com.example.cinderella.web.dto.UsersResponseDto;
import com.example.cinderella.domain.user.Users;
import com.example.cinderella.domain.user.UsersRepository;
import com.example.cinderella.web.dto.UsersSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatroomService {
    private final UsersRepository usersRepository;
    private final ChatRepository chatRepository;
    @Transactional(readOnly = true)
    public List<UsersResponseDto> findAllByChatid(Long chatid) {
        List<Users> entity = usersRepository.findAllByChatid(chatid);
        return entity.stream()
                .map(UsersResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * User entity에 현재 들어가있는 방 id를 갱신
     */
    @Transactional
    public void updateChatid(String email, Long chatid) {
        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 정보가 없습니다. email=" + email));
        users.updateChatid(chatid);
    }

    /**
     * 택시팟에 들어있는 유저 수를 업데이트
     */
    @Transactional
    public void updateNumofpeople(Long chatid) {
        Chat chat = chatRepository.findById(chatid)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 정보가 없습니다. chatid=" + chatid));
        chat.update();
    }

    /**
     * chat과 user에 현재 dest를 업데이트
     */
    @Transactional
    public void updateDest(String email, Long chatid, String dest) {
        Chat chat = chatRepository.findById(chatid)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 정보가 없습니다. chatid=" + chatid));
        chat.updateDest(dest);

        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 정보가 없습니다. email=" + email));
        users.updateDest(dest);
    }
}
