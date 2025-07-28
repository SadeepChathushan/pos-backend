package com.aasait.pos.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    private Double amount;

    private LocalDateTime paidAt;

    @ManyToOne
    private SalesInvoice salesInvoice;
}
