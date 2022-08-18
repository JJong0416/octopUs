package com.octopus.domain.pk;

import com.octopus.domain.Mission;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MissionPk implements Serializable {

    private static final long serialVersionUID = -2929789292155268166L;

    private Long missionTimeNo;

    private Mission mission;

}
