package com.aasait.pos.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue
    private Long pId;

    private Double amount;
    private String paymentMethod;

    @ManyToOne
    private SalesInvoice salesInvoice;
}
