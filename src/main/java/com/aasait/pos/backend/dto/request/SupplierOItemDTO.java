package com.aasait.pos.backend.dto.request;

import lombok.Data;

@Data
public class SupplierOItemDTO {
    private Long itemId;
    private int quantity;
    private double unitPrice;


}
