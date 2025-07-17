package com.aasait.pos.backend.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class AddSalesInvoiceDTO {
    private Long cashierId;
    private List<SalesItemDTO> items;
    private double paidAmount;
}
