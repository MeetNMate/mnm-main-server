package com.project.mnm.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_chatting")
public class UserChatting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "uid")
    private User uid;
    @ManyToOne
    @JoinColumn(name = "cid")
    private ChattingRoom cid;
    @Column(name = "last_access_at")
    private Timestamp lastAccessAt;
}
