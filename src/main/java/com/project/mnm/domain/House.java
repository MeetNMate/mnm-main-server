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
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "create_at")
    private Timestamp createAt;
    @Column(name = "updated_at")
    private Timestamp updateAt;
    @Column(name = "delete_at")
    private Timestamp deleteAt;
    @Column(name = "last_access_at")
    private Timestamp lastAccessAt;
    @Column(name = "capacity")
    private Timestamp capacity;
    @ManyToOne
    @JoinColumn(name = "captain_id")
    private User captain_id;
    @Column(name = "location")
    private Timestamp location;
}
