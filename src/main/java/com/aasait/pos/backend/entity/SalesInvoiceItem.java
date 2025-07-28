package com.aasait.pos.backend.entity;

import jakarta.persistence.*;
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

    @ManyToOne
    private Item item; // ✅ Link to Item entity
}
