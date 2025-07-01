package com.aasait.pos.backend.service.IMPL;

import com.aasait.pos.backend.dto.request.AddSupplierDTO;
import com.aasait.pos.backend.entity.Supplier;
import com.aasait.pos.backend.repository.SupplierRepo;
import com.aasait.pos.backend.service.StockService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockServiceIMPL implements StockService {

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AddSupplierDTO registerSupplier(AddSupplierDTO addSupplierDTO) {

        Optional<Supplier> existingSupplierByEmail = supplierRepo.findByEmail(addSupplierDTO.getEmail());
        if (existingSupplierByEmail.isPresent()) {
            throw new IllegalArgumentException("Supplier with email " + addSupplierDTO.getEmail() + " already exists");
        }

        // Convert DTO to Entity
        Supplier supplier = modelMapper.map(addSupplierDTO, Supplier.class);

        // Save supplier
        Supplier savedSupplier = supplierRepo.save(supplier);

        // Convert back Entity to DTO and return
        return modelMapper.map(savedSupplier, AddSupplierDTO.class);
    }

}
