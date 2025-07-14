package com.aasait.pos.backend.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesInvoiceDTO {
    private Long cashierId;
    private Long invoiceCount;
}
