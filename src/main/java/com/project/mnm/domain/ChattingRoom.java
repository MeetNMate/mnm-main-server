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
@Table(name = "chatting_rooms")
public class ChattingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "request_at")
    private Timestamp requestAt;
    @ManyToOne
    @JoinColumn(name = "request_uid")
    private User requestUser;
    @Column(name = "request_success")
    private boolean requestSuccess;
}