package com.aasait.pos.backend.repository;

import com.aasait.pos.backend.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customers, Long> {
}
