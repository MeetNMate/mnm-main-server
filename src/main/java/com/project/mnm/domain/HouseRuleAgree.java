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
@Table(name = "house_rule_agree")
public class HouseRuleAgree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "hid")
    private House hid;
    @ManyToOne
    @JoinColumn(name = "uid")
    private User uid;
    @Column(name = "agree_at")
    private Timestamp agreeAt;
    @Column(name = "agree_or_not")
    private boolean agreeOrNot;
    @Column(name = "disagree_reason")
    private String disagreeReason;
    @Column(name = "lower_num")
    private int lowerNum;
    @Column(name = "create_at")
    private Timestamp createAt;
    @Column(name = "updated_at")
    private Timestamp updateAt;
    @Column(name = "delete_at")
    private Timestamp deleteAt;
}
