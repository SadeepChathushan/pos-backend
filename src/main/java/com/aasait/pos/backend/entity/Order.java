package com.aasait.pos.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private String batchNo;
    private String status;
    private double total;
    private double paidAmount;
    private double payableAmount;
    private LocalDate orderDate;

    @ManyToOne
    private Supplier supplierId;
}
