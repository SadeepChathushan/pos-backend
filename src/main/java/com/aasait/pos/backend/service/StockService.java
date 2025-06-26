package com.aasait.pos.backend.service;

import com.aasait.pos.backend.dto.request.AddSupplierDTO;

public interface StockService {
    AddSupplierDTO registerSupplier(AddSupplierDTO addSupplierDTO);
}
