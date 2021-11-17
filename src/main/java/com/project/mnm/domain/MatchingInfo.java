package com.project.mnm.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "matching_infos")
public class MatchingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "uid")
    private User user;
    @Column(name = "mbti")
    private String mbti;
    @Column(name = "user_smoking")
    private String userSmoking;
    @Column(name = "mate_smoking")
    private String mateSmoking;
    @Column(name = "user_pet")
    private Boolean userPet;
    @Column(name = "user_pet_dog")
    private Boolean userPetDog;
    @Column(name = "user_pet_cat")
    private Boolean userPetCat;
    @Column(name = "user_pet_reptile_fish")
    private Boolean userPetReptileFish;
    @Column(name = "user_pet_rodent")
    private Boolean userPet_bird;
    @Column(name = "user_pet_bird")
    private Boolean userPetBird;
    @Column(name = "user_pet_etc")
    private String userPetEtc;
    @Column(name = "mate_pet")
    private String matePet;
    @Column(name = "mate_pet_dog")
    private Boolean matePetDog;
    @Column(name = "mate_pet_cat")
    private Boolean matePetCat;
    @Column(name = "mate_pet_reptile_fish")
    private Boolean matePetReptileFish;
    @Column(name = "mate_pet_rodent")
    private Boolean matePet_bird;
    @Column(name = "mate_pet_bird")
    private Boolean matePetBird;
    @Column(name = "mate_pet_etc")
    private String matePetEtc;
    @Column(name = "air_like_airconditioner")
    private String airLikeAirconditioner;
    @Column(name = "air_night_airconditioner")
    private String airNightAirconditioner;
    @Column(name = "air_like_heater")
    private String airLikeHeater;
    @Column(name = "air_none")
    private Boolean airNone;
    @Column(name = "user_bug_killer")
    private String userBugKiller;
    @Column(name = "mate_bug_killer")
    private String mateBugKiller;
    @Column(name = "usesr_cooking")
    private String userCooking;
    @Column(name = "mate_cooking")
    private String mateCooking;
    @Column(name = "eat_together")
    private String eatTogether;
    @Column(name = "share_item")
    private String shareItem;
    @Column(name = "mate_alcohol")
    private String mateAlcohol;
    @Column(name = "mate_clean")
    private String mateClean;
    @Column(name = "permission_to_enter")
    private String permissionToEnter;
}
