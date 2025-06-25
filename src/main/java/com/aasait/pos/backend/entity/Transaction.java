package com.aasait.pos.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private OurUsers userId;
}
