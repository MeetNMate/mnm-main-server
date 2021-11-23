package com.project.mnm.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "evaluations")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "appraiser")
    private User appraiser;
    @ManyToOne
    @JoinColumn(name = "appraisee")
    private User appraisee;
    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;
    @Column(name = "score")
    private int score;
    @Column(name = "content")
    private String content;
    @CreationTimestamp
    @Column(name = "create_at")
    private Timestamp createAt;
}
