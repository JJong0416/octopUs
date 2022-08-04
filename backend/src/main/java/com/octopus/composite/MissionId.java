package com.octopus.composite;


import com.octopus.domain.Mission;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MissionId implements Serializable {

    private static final long serialVersionUID = -2929789292155268166L;

    private Long missionTimeNo;

    private Mission mission;

}
