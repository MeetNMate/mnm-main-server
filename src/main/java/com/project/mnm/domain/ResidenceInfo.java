package com.project.mnm.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private long id;
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
