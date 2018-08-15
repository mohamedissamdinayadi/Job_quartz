package com.squeezer.oauth2.data.repository;

import com.squeezer.oauth2.data.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    Authority findOneByName(String name);
}
