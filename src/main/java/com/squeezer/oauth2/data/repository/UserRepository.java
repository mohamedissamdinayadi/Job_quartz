package com.squeezer.oauth2.data.repository;


import com.squeezer.oauth2.data.entity.Authority;
import com.squeezer.oauth2.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * User repository for CRUD operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    // Returns a user by his username
    Optional<User> findByUsername(String username);

    // Returns list of users by one role
    List<User> findOneByAuthority(Authority authority);
}
