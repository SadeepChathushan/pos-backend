package com.aasait.pos.backend.repository;

import com.aasait.pos.backend.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepo extends JpaRepository<AuditLog,Long> {

}
