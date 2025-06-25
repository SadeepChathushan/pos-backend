package com.aasait.pos.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Supplier {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String address;
    private String contactNo;
    private String email;
}
