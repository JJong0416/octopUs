package com.octopus.domain;

import com.octopus.domain.PK.OctopusPK;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Table(name = "octopus_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(OctopusPK.class)
public class Octopus {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_no", referencedColumnName = "user_no")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "mission_no", referencedColumnName = "mission_no")
    private Mission mission;

}
