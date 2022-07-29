package com.octopus.service;

import com.octopus.domain.Mission;
import com.octopus.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    @Transactional(readOnly = true)
    public List<Mission> getNewMissions(){
        return missionRepository.findTop5By(Sort.by(Sort.Direction.DESC,"missionNo"));
    }
}
