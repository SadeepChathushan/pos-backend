package com.aasait.pos.backend.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
}
