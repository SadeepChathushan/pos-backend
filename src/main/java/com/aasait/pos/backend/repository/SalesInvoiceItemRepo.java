package com.aasait.pos.backend.repository;

import com.aasait.pos.backend.entity.SalesInvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesInvoiceItemRepo extends JpaRepository<SalesInvoiceItem, Long> {
}
