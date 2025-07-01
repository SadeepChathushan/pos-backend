package com.aasait.pos.backend.dto.response;

import lombok.Data;

@Data
public class SupplierDTO {
    private Long id;
    private String name;
    private String address;
    private String contactNo;
    private String email;
}
