package com.aasait.pos.backend.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class Liability {
    @Id
    @GeneratedValue
    private Long lId;

    private Double amount;
    private String note;
    private LocalDate date;

    @ManyToOne
    private Transaction transaction;
}
