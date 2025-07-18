package com.aasait.pos.backend.dto.response;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private String itemName;   // Extracted from related Item entity
    private int quantity;
    private double unitPrice;
}
