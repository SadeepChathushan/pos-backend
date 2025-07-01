package com.aasait.pos.backend.dto.request;

import lombok.Data;

@Data
public class AddItemDTO {
    private String batchNum;
    private String name;
    private String category;
    private String remarks;
    private String brand;

}
