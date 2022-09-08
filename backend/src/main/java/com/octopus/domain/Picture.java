package com.octopus.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Table(name = "picture")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_no", unique = true)
    private Long pictureNo;

    @ManyToOne
    @JoinColumn(name = "mission_no")
    private Mission missionNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User userNo;

    @Column(name = "picture_url", nullable = false)
    private String pictureUrl;

    @Column(name = "picture_date", nullable = false)
    private LocalDateTime date;

    @Builder(builderMethodName = "createPicture")
    Picture(Mission missionNo, User userNo, String pictureUrl){
        this.missionNo = missionNo;
        this.userNo = userNo;
        this.pictureUrl = pictureUrl;
        this.date = LocalDateTime.now();
    }
}
