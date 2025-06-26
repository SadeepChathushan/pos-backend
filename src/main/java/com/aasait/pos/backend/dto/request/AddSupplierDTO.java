package com.aasait.pos.backend.dto.request;

import lombok.Data;

@Data
public class AddSupplierDTO {
    private String name;
    private String address;
    private String contactNo;
    private String email;
}

