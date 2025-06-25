package com.aasait.pos.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class SalesInvoice {
    @Id
    @GeneratedValue
    private Long siId;

    private LocalDateTime dateTime;

//    @ManyToOne
//    private Customer customer;

    @OneToMany(mappedBy = "salesInvoice")
    private List<SalesInvoiceItem> items;

    @OneToMany(mappedBy = "salesInvoice")
    private List<Payment> payments;
}
