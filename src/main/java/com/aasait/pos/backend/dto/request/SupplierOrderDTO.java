package com.aasait.pos.backend.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SupplierOrderDTO {
    private String batchNo;
    private String status;
    private double total;
    private double paidAmount;
    private double payableAmount;
    private LocalDate orderDate;
    private Long supplierId;
    private List<SupplierOItemDTO> items;
}
