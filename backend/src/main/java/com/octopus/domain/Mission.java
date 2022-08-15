package com.octopus.domain;

import com.octopus.dto.request.MissionCreateReq;
import com.octopus.dto.request.MissionUpdateInfoReq;
import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.domain.type.MissionType;
import com.octopus.exception.CustomException;
import com.octopus.exception.ErrorCode;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "mission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_no", unique = true)
    private Long missionNo;

    @Column(name = "mission_code", length = 8, nullable = false, unique = true)
    private String missionCode;

    @Column(name = "mission_leader_id", length = 15, nullable = false)
    private String missionLeaderId;

    @Column(name = "mission_title", length = 30, nullable = false)
    private String missionTitle;

    @Column(name = "mission_type", nullable = false)
    private MissionType missionType;

    @Column(name = "mission_point", nullable = false)
    private Integer missionPoint;

    @Column(name = "mission_status", nullable = false)
    private MissionStatus missionStatus;

    @Column(name = "mission_limit_personnel", nullable = false)
    private Integer missionLimitPersonnel;

    @Column(name = "mission_users", length = 100)
    private String missionUsers;

    @Column(name = "mission_content", length = 300, nullable = false)
    private String missionContent;

    @Column(name = "mission_open", nullable = false)
    private MissionOpenType missionOpen;

    @OneToOne(mappedBy = "mission")
    private MissionTime missionTime;

    // TODO: 2022-07-29 MapStruct 고민해보기 너무 길긴 하다..
    @Builder(builderMethodName = "createMission")
    Mission(MissionCreateReq missionCreateReq) {
        this.missionCode = missionCreateReq.getMissionCode();
        this.missionLeaderId = missionCreateReq.getMissionLeaderId();
        this.missionTitle = missionCreateReq.getMissionTitle();
        this.missionType = missionCreateReq.getMissionType();
        this.missionPoint = missionCreateReq.getMissionPoint();
        this.missionStatus = missionCreateReq.getMissionStatus();
        this.missionLimitPersonnel = missionCreateReq.getMissionLimitPersonnel();
        this.missionUsers = missionCreateReq.getMissionUser();
        this.missionContent = missionCreateReq.getMissionContent();
        this.missionOpen = missionCreateReq.getMissionOpen();
    }

    public void updateMission(MissionUpdateInfoReq missionUpdateInfoReq){
        this.missionTitle = missionUpdateInfoReq.getMissionTitle();
        this.missionContent = missionUpdateInfoReq.getMissionContent();
        this.missionType = missionUpdateInfoReq.getMissionType();
        if (missionUpdateInfoReq.getMissionLimitPersonnel() < this.missionLimitPersonnel){
            throw new CustomException(ErrorCode.LIMIT_CANT_BE_LESS_THAN_BEFORE);
        }
        else
        {this.missionLimitPersonnel = missionUpdateInfoReq.getMissionLimitPersonnel();}
    }

    public void updateMissionUsers(String missionUsers){this.missionUsers = missionUsers;}

    public void updateMissionStatus(MissionStatus missionStatus){this.missionStatus = missionStatus;}


}
