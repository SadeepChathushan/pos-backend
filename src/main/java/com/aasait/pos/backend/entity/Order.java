package com.aasait.pos.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")              // “order” is a SQL keyword, so keep it plural
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String batchId;
    private double quantity;
    private double unitPrice;
    private double sellPrice;

    /* -------- owning side Item‑›Order -------- */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id")     // FK column in orders
    private Item item;

    /* -------- reverse side Order‑›Invoice -------- */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invoice> invoices = new ArrayList<>();
}
