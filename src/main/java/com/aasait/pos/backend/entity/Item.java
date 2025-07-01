package com.aasait.pos.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String batchNum;
    private String name;
    private String category;
    private String remarks;
    private String brand;



}
