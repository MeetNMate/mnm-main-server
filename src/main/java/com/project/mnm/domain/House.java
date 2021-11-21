package com.project.mnm.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @CreationTimestamp
    @Column(name = "create_at")
    private Timestamp createAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updateAt;
    @Column(name = "last_access_at")
    private Timestamp lastAccessAt;
    @Column(name = "capacity")
    private int capacity;
    @ManyToOne
    @JoinColumn(name = "captain_id")
    private User firstCaptain;
    @ManyToOne
    @JoinColumn(name = "captain_id")
    private User secondCaptain;
    @Column(name = "location")
    private String location;
}
