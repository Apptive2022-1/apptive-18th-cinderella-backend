package com.example.cinderella.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    @Modifying(clearAutomatically = true)
    List<Users> findAllByChatid(Long chatid);

}
