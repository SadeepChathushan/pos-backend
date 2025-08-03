package com.aasait.pos.backend.dto.response;

import lombok.Data;

@Data
public class OrderBatchDTO {
    private String batchId;
    private String dateAdded; // Use String for frontend, or LocalDate
    private double sellPrice;
    private double unitPrice;
    private int quantity;
}
