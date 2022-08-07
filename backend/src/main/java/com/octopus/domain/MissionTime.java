package com.octopus.domain;

import com.octopus.composite.MissionId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Table(name = "mission_time")
//@IdClass(MissionId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MissionTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_time_no")
    private Long missionTimeNo;

//    @Id
    @OneToOne
    @JoinColumn(name = "mission_no")
    private Mission mission;

    @Column(name = "mission_time_start_time")
    private LocalDateTime missionTimeStartTime;

    @Column(name = "mission_time_week")
    private Integer missionTimeWeek;

    @Column(name = "mission_time_dpw")
    private Integer missionTimeDPW;

    @Column(name = "mission_time_tpd")
    private Integer missionTimeTPD;

    @Builder(builderMethodName = "createMTBuilder")
    public MissionTime(
            Mission mission, Integer missionTimeWeek, Integer missionTimeDPW, Integer missionTimeTPD
    ) {
        this.mission = mission;
        this.missionTimeStartTime = LocalDateTime.now();
        this.missionTimeWeek = missionTimeWeek;
        this.missionTimeDPW = missionTimeDPW;
        this.missionTimeTPD = missionTimeTPD;
    }
}
