package com.aasait.pos.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class GRN {
    @Id
    @GeneratedValue
    private Long id;

    private String receivedDate;

    @ManyToOne
    private Order orderId;
}
