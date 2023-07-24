package com.example.cinderella.domain.chat;

import com.example.cinderella.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Chat extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String host;
    @Column(nullable = false)
    private String start;
    @Column(nullable = false)
    @ElementCollection
//    private String dest;
    private List<String> dest;
    @Column(nullable = false)
    private int time;
    @Column(nullable = false)
    private int num_of_people;
    @Column
    private String calcTime;
    @Column
    private String openChatLink;
    @Column
    private String chatName;

    @Builder
    public Chat(String host, String start, List dest, int time, int num_of_people, String calcTime, String openChatLink, String chatName) {
        this.host = host;
        this.start = start;
        this.dest = dest;
        this.time = time;
        this.num_of_people = num_of_people;
        this.calcTime = calcTime;
        this.openChatLink = openChatLink;
        this.chatName = chatName;
    }

    public void setStartAndTime(String changedStart ,String calcTime) {
        this.start = changedStart;
        this.calcTime = calcTime;
    }

    /**
     * 오픈챗 링크 변경
     */
    public void changeOpenChatLink(String openChatLink){
        this.openChatLink = openChatLink;
    }

    public void update() {
        this.num_of_people++;
    }

    public void updateDest(String destination) {
        this.dest.add(destination);
    }

}
