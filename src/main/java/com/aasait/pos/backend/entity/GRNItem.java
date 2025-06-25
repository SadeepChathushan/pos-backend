package com.aasait.pos.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class GRNItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private GRN grnId;

    @ManyToOne
    private Item itemId;

    @ManyToOne
    private GRNItem grnItemId;

    private int quantity;
    private double cost;
}
