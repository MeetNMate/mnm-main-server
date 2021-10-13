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
@Table(name = "matching_infos")
public class MatchingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "uid")
    private User user;
    @Column(name = "mbti")
    private String mbti;
    @Column(name = "user_smoking")
    private String userSmoking;
}
