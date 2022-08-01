package com.octopus.domain.PK;

import com.octopus.domain.Mission;
import com.octopus.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OctopusPK implements Serializable {
    private User user;
    private Mission mission;
}
