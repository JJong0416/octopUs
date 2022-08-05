package com.octopus.repository;

import com.octopus.domain.Mission;
import com.octopus.domain.Picture;
import com.octopus.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface PictureRepository extends JpaRepository<Picture, Long> {

    Optional<List<Picture>> findPicturesByMissionNoAndUserNo(Mission mission, User user);
}
