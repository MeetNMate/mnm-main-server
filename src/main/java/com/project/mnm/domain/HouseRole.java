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
@Table(name = "house_role")
public class HouseRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "hid")
    private House house;
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;
    @Column(name = "create_at")
    private Timestamp createAt;
    @Column(name = "updated_at")
    private Timestamp updateAt;
    @Column(name = "delete_at")
    private Timestamp deleteAt;
    @Column(name = "role")
    private String role;
    @Column(name = "week")
    private int week;
    @Column(name = "start_at")
    private Timestamp startAt;
    @Column(name = "routine")
    private int routine;

}
