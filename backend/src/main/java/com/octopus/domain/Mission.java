package com.octopus.domain;

import com.octopus.domain.dto.MissionCreateDto;
import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.domain.type.MissionType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "mission_name", length = 30, nullable = false)
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

    @ManyToMany
    @JoinTable(
            name = "octopus_table",
            joinColumns = @JoinColumn(name = "mission_no"),
            inverseJoinColumns = @JoinColumn(name = "user_no")
    )
    private final Set<User> users = new HashSet<>();

    // TODO: 2022-07-29 MapStruct 고민해보기 너무 길긴 하다..
    @Builder(builderMethodName = "createMission")
    Mission(MissionCreateDto missionCreateDto) {
        this.missionCode = missionCreateDto.getMissionCode();
        this.missionLeaderId = missionCreateDto.getMissionLeaderId();
        this.missionTitle = missionCreateDto.getMissionTitle();
        this.missionType = missionCreateDto.getMissionType();
        this.missionPoint = missionCreateDto.getMissionPoint();
        this.missionStatus = missionCreateDto.getMissionStatus();
        this.missionLimitPersonnel = missionCreateDto.getMissionLimitPersonnel();
        this.missionUsers = missionCreateDto.getMissionUser();
        this.missionContent = missionCreateDto.getMissionContent();
        this.missionOpen = missionCreateDto.getMissionOpen();
    }

    public void updateMissionUsers(String missionUsers){this.missionUsers = missionUsers;}
}
