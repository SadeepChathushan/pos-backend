package com.aasait.pos.backend.repository;

import com.aasait.pos.backend.entity.AuditLog;
import com.aasait.pos.backend.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByToken(String token);
}
