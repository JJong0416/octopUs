package com.octopus.domain;

import com.octopus.domain.PK.OctopusPK;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no", referencedColumnName = "user_no")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_no", referencedColumnName = "mission_no")
    private Mission mission;

}
