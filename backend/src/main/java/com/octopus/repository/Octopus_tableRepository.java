package com.octopus.repository;

import com.octopus.domain.Mission;
import com.octopus.domain.Octopus;
import com.octopus.domain.PK.OctopusPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Octopus_tableRepository extends JpaRepository<Octopus, OctopusPK> {
}
