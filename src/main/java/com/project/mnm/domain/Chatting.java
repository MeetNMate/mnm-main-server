package com.project.mnm.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chattings")
public class Chatting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "cid")
    private ChattingRoom chattingRoom;
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;
    @Column(name = "message")
    private String message;
    @CreationTimestamp
    @Column(name = "send_at")
    private Timestamp sendAt;
    @Column(name = "is_request")
    private Boolean isRequest;
}
