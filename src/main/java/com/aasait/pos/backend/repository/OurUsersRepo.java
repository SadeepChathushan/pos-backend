package com.aasait.pos.backend.repository;

import com.aasait.pos.backend.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OurUsersRepo extends JpaRepository<OurUsers, Integer> {
    Optional<OurUsers> findByEmail(String email);
}
