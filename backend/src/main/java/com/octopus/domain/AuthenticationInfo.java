package com.octopus.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Table(name = "authentication_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthenticationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authentication_no", unique = true)
    private Long authenticationNo;

    @ManyToOne
    @JoinColumn(name = "mission_no")
    private Mission mission;

    @Column(name = "authentication_start_time")
    private Date authenticationStartTime;

    @Column(name = "authentication_end_time")
    private Date authenticationEndTime;

}
