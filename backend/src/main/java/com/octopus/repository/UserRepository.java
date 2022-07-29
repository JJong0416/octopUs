package com.octopus.repository;


import com.octopus.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserId(String userId);

    Optional<User> findByUserNickname(String userNickname);

}