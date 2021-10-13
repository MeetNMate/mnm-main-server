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
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "type")
    private boolean type;
    @Column(name = "user_matching")
    private boolean userMatching;
    @Column(name = "create_at")
    private Timestamp createAt;
    @Column(name = "remove_at")
    private Timestamp removeAt;
}
