package com.aasait.pos.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class SalesInvoiceItem {
    @Id
    @GeneratedValue
    private Long siiId;

    private Double price;
    private Integer quantity;
    private Double discount;
    private LocalDateTime dateTime;

    @ManyToOne
    private SalesInvoice salesInvoice;

//    @ManyToOne
//    private Item item;
}
