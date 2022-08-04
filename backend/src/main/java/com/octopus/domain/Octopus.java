package com.octopus.domain;

import com.octopus.domain.PK.OctopusPK;
import com.octopus.domain.dto.OctopusDto;
import lombok.*;

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

    @Builder(builderMethodName = "addToOctopus")
    public Octopus(
            User user, Mission mission
    ) {
        this.user = user;
        this.mission = mission;
    }


}
