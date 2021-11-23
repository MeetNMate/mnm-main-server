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
@Table(name = "user_house")
public class UserHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;
    @ManyToOne
    @JoinColumn(name = "hid")
    private House house;
    @Column(name = "last_access_at")
    private Timestamp lastAccessAt;
    @Column(name = "join_at")
    private Timestamp joinAt;
    @Column(name = "exit_at")
    private Timestamp exitAt;
    @Column(name = "is_eliminated")
    private boolean isEliminated;
}
