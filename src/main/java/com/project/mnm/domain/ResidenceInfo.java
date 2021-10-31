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
@Table(name = "residence_infos")
public class ResidenceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "uid")
    private User user;
    @Column(name = "headcount")
    private int headcount;
    @Column(name = "location")
    private String location;
    @Column(name = "budget")
    private int budget;
    @Column(name = "term_start")
    private Timestamp termStart;
    @Column(name = "term_end")
    private Timestamp termEnd;
}
