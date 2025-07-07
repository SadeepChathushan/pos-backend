package com.aasait.pos.backend.service;

import com.aasait.pos.backend.dto.request.AddItemDTO;
import com.aasait.pos.backend.dto.request.AddSupplierDTO;
import com.aasait.pos.backend.dto.request.SupplierOrderDTO;
import com.aasait.pos.backend.dto.response.SupplierDTO;

import java.util.List;

public interface StockService {
    AddSupplierDTO registerSupplier(AddSupplierDTO addSupplierDTO);

    List<SupplierDTO> getAllSupplier();

    AddItemDTO addItem(AddItemDTO addItemDTO);

    List<String> getAllSupplierNames();

    List<String> getAllItemsName();

    void saveSupplierOrder(SupplierOrderDTO supplierOrderDTO);
}
