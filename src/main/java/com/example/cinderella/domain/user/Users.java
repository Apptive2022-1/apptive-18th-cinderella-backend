package com.example.cinderella.domain.user;

import com.example.cinderella.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private Long chatid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;

    @Column
    private String dest;

    // 차단목록
    @ElementCollection
    @Column
    private Set<String> blockList = new HashSet<>();

    // 현재 택시팟에 참여중인지 유무
    @Column
    private Boolean participating = false;


    @Builder
    public Users(String name, String email, Role role, Gender gender, String dest, HashSet<String> blockList) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.gender = gender;
        this.dest = dest;
        this.blockList = blockList;
    }

    public Users update(String name) {
        this.name = name;
        return this;
    }

    public Users updateChatid(Long chatid) {
        this.chatid = chatid;
        return this;
    }

    public void joinGroup(){
        this.participating = true;
    }

    public void leaveGroup(){
        this.participating = false;
    }

    public void updateDest(String dest) {
        this.dest = dest;
    }


    public String getRoleKey() {
        return this.role.getKey();
    }

    public void signUp(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }


    /**
     * 블랙리스트에 추가 및 제거
     */
    public void addBlockList(String email) {
        this.blockList.add(email);
    }

    public void removeBlockList(String email) {
        this.blockList.remove(email);
    }
}
