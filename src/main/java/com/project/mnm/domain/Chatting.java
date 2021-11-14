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
@Table(name = "chatting")
public class Chatting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "cid")
    private ChattingRoom cid;
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;
    @Column(name = "send_at")
    private Timestamp sendAt;
    @Column(name = "message")
    private String message;
    @Column(name = "is_request")
    private boolean isRequest;
}
