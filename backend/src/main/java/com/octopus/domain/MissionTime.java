package com.octopus.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Table(name = "mission_time")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MissionTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne
    @JoinColumn(name = "mission_no", unique = true)
    private Mission mission;

    @Column(name = "mission_time_start_time")
    private Date missionTimeStartTime;

    @Column(name = "mission_time_week")
    private Integer missionTimeWeek;

    @Column(name = "mission_time_dpw")
    private Integer missionTimeDPW;

    @Column(name = "mission_time_tpd")
    private Integer missionTimeTPD;
}
