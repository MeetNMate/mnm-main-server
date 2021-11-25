package com.project.mnm.domain;

import lombok.*;

import javax.persistence.*;

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
    private int userSmoking;
    @Column(name = "mate_smoking")
    private int mateSmoking;
    @Column(name = "user_pet")
    private int userPet;
    @Column(name = "user_pet_dog")
    private int userPetDog;
    @Column(name = "user_pet_cat")
    private int userPetCat;
    @Column(name = "user_pet_reptile_fish")
    private int userPetReptileFish;
    @Column(name = "user_pet_rodent")
    private int userPetRodent;
    @Column(name = "user_pet_bird")
    private int userPetBird;
    @Column(name = "user_pet_etc")
    private String userPetEtc;
    @Column(name = "mate_pet")
    private int matePet;
    @Column(name = "mate_pet_dog")
    private int matePetDog;
    @Column(name = "mate_pet_cat")
    private int matePetCat;
    @Column(name = "mate_pet_reptile_fish")
    private int matePetReptileFish;
    @Column(name = "mate_pet_rodent")
    private int matePetRodent;
    @Column(name = "mate_pet_bird")
    private int matePetBird;
    @Column(name = "mate_pet_etc")
    private String matePetEtc;
    @Column(name = "air_like_airconditioner")
    private int airLikeAirconditioner;
    @Column(name = "air_like_heater")
    private int airLikeHeater;
    @Column(name = "user_bug_killer")
    private int userBugKiller;
    @Column(name = "mate_bug_killer")
    private int mateBugKiller;
    @Column(name = "user_cooking")
    private int userCooking;
    @Column(name = "mate_cooking")
    private int mateCooking;
    @Column(name = "eat_together")
    private int eatTogether;
    @Column(name = "share_item")
    private int shareItem;
    @Column(name = "mate_alcohol")
    private int mateAlcohol;
    @Column(name = "mate_clean")
    private int mateClean;
    @Column(name = "permission_to_enter")
    private int permissionToEnter;
    @Column(name = "noise_talking")
    private int noiseTalking;
    @Column(name = "noise_music")
    private int noiseMusic;
    @Column(name = "noise_alarm")
    private int noiseAlarm;
}
