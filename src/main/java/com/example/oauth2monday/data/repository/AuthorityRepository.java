package com.example.oauth2monday.data.repository;

import com.example.oauth2monday.data.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    Authority findOneByName(String name);
}
