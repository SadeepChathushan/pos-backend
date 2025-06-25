package com.aasait.pos.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ShowroomUpdateLog {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime dateTime;
    private int quantity;

    @ManyToOne
    private Item itemId;
}
