// src/main/java/com/aasait/pos/backend/dto/response/CashierDTO.java
package com.aasait.pos.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // ✅ Add this
@AllArgsConstructor // ✅ Optional: if you want all-args constructor
public class CashierDTO {
    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;
}
