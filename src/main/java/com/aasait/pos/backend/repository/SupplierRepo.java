package com.aasait.pos.backend.repository;

import com.aasait.pos.backend.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier,Long> {
    Optional<Supplier> findByEmail(String email);
}
