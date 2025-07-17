package com.aasait.pos.backend.service;

import com.aasait.pos.backend.entity.AuditLog;
import com.aasait.pos.backend.repository.AuditLogRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
@RequiredArgsConstructor
public class AuditLogService {
    private final AuditLogRepo repo;

    @Async
    public void log(String actor, String action, String ip, String details){
        repo.save(new AuditLog(null, Instant.now(),actor,action,ip,details));
    }
}
