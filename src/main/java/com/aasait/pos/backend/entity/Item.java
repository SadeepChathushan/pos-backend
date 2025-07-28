package com.aasait.pos.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String batchNum;
    private String name;
    private String category;
    private String remarks;
    private String brand;

    private Double price;

    private Integer quantity;
    // ✅ Make sure this exists
}
