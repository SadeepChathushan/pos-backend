package com.aasait.pos.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "orders")
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
