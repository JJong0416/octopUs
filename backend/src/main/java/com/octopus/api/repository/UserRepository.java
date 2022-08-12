package com.octopus.api.repository;


import com.octopus.domain.User;
import com.octopus.domain.type.PlatformType;
import com.octopus.dto.layer.UserListDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Long> {

    Boolean existsUserByUserId(String userId);

    Boolean existsUserByUserNickname(String userNickname);

    Optional<User> findUserByUserIdAndPlatformType(String userId, PlatformType platformType);

    Optional<User> findUserByUserEmail(String userEmail);

    Optional<User> findByUserId(String userId);

    Optional<User> findByUserNickname(String userNickname);

    List<UserListDto> findUserByUserIdOrUserEmailOrUserNickname(String userId, String userEmail, String userNickname);
}