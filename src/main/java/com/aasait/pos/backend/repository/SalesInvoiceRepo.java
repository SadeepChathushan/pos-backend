package com.aasait.pos.backend.repository;

import com.aasait.pos.backend.entity.SalesInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // ✅ Add this import
import org.springframework.stereotype.Repository;

@Repository
public interface SalesInvoiceRepo extends JpaRepository<SalesInvoice, Long> {
    Long countByCashier_Id(Long cashierId);

    @Query("SELECT SUM(s.totalAmount) FROM SalesInvoice s WHERE s.cashier.id = :cashierId")
    Double getTotalAmountByCashierId(Long cashierId);
}
