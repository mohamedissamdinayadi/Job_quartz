package com.example.oauth2monday.data.repository;


import com.example.oauth2monday.data.entity.Authority;
import com.example.oauth2monday.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * User repository for CRUD operations.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    List<User> findOneByAuthority(Authority authority);
}
