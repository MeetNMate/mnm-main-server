package com.project.mnm.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "memo")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private Long id;
    @Column(length = 200, nullable = false)
    private String memoText;
}
