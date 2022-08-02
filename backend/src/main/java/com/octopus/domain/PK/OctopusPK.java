package com.octopus.domain.PK;

import com.octopus.domain.Mission;
import com.octopus.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OctopusPK implements Serializable {
    private User user;
    private Mission mission;
}
