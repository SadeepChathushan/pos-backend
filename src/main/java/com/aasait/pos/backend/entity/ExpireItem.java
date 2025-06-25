package com.aasait.pos.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ExpireItem {
    @Id
    @GeneratedValue
    private Long id;

    private String reason;
    private int quantity;

//    @ManyToOne
//    private GRN grn;
}
