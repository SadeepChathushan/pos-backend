package com.aasait.pos.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    private int quantity;
    private double unitPrice;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Item item;
}
