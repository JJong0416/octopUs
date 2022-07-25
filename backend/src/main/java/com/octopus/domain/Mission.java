package com.octopus.domain;

import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.domain.type.MissionType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String missionName;

    @Enumerated(EnumType.STRING)
    @Column(name = "mission_type", nullable = false)
    private MissionType missionType;

    @Column(name = "mission_point", nullable = false)
    private Integer missionPoint;

    @Enumerated(EnumType.STRING)
    @Column(name = "mission_status", nullable = false)
    private MissionStatus missionStatus;

    @Column(name = "mission_limit_personnel",nullable = false)
    private Integer missionLimitPersonnel;

    @Column(name = "mission_users", length = 100)
    private String missionUsers;

    @Column(name = "mission_content", length = 300, nullable = false)
    private String missionContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "mission_open", nullable = false)
    private MissionOpenType missionOpen;

    @ManyToMany
    @JoinTable(
            name = "octopus_table",
            joinColumns = @JoinColumn(name = "mission_no"),
            inverseJoinColumns = @JoinColumn(name = "user_no")
    )
    private final Set<User> users = new HashSet<>();

}
