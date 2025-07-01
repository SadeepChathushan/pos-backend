package com.aasait.pos.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse <T> {
    private LocalDateTime timestamp;
    private String message;
    private T data;
    private int status;
}
