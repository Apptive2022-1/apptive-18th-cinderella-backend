package com.example.cinderella.domain.chat;

import com.example.cinderella.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAllByStart(String start);
    Optional<Chat> findByid(Long id);
}
