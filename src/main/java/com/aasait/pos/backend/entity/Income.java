package com.aasait.pos.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Income {
    @Id
    @GeneratedValue
    private Long iId;

    private Double amount;
    private LocalDate date;
    private String note;

    @ManyToOne
    private Transaction transaction;
}
