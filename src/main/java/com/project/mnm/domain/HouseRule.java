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
@Table(name = "house_rule")
public class HouseRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "hid")
    private House house;
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;
    @Column(name = "original_rule")
    private String originalRule;
    @Column(name = "new_rule")
    private String newRule;
    @Column(name = "upper_num")
    private int upperNum;
    @Column(name = "lower_num")
    private int lowerNum;
    @CreationTimestamp
    @Column(name = "create_at")
    private Timestamp createAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updateAt;
    @Column(name = "agree_at")
    private Timestamp agreeAt;
}
