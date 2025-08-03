package com.aasait.pos.backend.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ItemWithBatchesDTO {
    private Long id;
    private String itemName;
    private List<OrderBatchDTO> batches;
}
