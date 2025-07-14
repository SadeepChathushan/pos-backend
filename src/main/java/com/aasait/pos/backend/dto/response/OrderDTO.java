package com.aasait.pos.backend.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String batchNo;
    private String status;
    private double total;
    private double paidAmount;
    private double payableAmount;
    private LocalDate orderDate;
    private String supplierName; // Extracted from Supplier entity
    private List<OrderItemDTO> items;
}
