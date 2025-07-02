package com.aasait.pos.backend.service.IMPL;

import com.aasait.pos.backend.dto.request.AddItemDTO;
import com.aasait.pos.backend.dto.request.AddSupplierDTO;
import com.aasait.pos.backend.dto.response.SupplierDTO;
import com.aasait.pos.backend.entity.Item;
import com.aasait.pos.backend.entity.Supplier;
import com.aasait.pos.backend.exception.ResourceAlreadyExistsException;
import com.aasait.pos.backend.repository.ItemRepo;
import com.aasait.pos.backend.repository.SupplierRepo;
import com.aasait.pos.backend.service.StockService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceIMPL implements StockService {

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AddSupplierDTO registerSupplier(AddSupplierDTO addSupplierDTO) {

        Optional<Supplier> existingSupplierByEmail = supplierRepo.findByEmail(addSupplierDTO.getEmail());
        if (existingSupplierByEmail.isPresent()) {
            throw new IllegalArgumentException("Supplier with email " + addSupplierDTO.getEmail() + " already exists");
        }

        // Check by name
        Optional<Supplier> existingSupplierByName = supplierRepo.findByName(addSupplierDTO.getName());
        if (existingSupplierByName.isPresent()) {
            throw new IllegalArgumentException("Supplier with name " + addSupplierDTO.getName() + " already exists");
        }


        // Convert DTO to Entity
        Supplier supplier = modelMapper.map(addSupplierDTO, Supplier.class);

        // Save supplier
        Supplier savedSupplier = supplierRepo.save(supplier);

        // Convert back Entity to DTO and return
        return modelMapper.map(savedSupplier, AddSupplierDTO.class);
    }

    @Override
    public List<SupplierDTO> getAllSupplier() {
        return supplierRepo.findAll()
                .stream()
                .map(supplier -> modelMapper.map(supplier, SupplierDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddItemDTO addItem(AddItemDTO addItemDTO) {
        if (itemRepo.existsByName(addItemDTO.getName())){
            throw new ResourceAlreadyExistsException("Item already exists: " + addItemDTO.getName());
        }

        Item item = modelMapper.map(addItemDTO, Item.class);
        Item savedItem = itemRepo.save(item);
        return modelMapper.map(savedItem, AddItemDTO.class);
    }

    @Override
    public List<String> getAllSupplierNames() {
        List<String> names = supplierRepo.findAllSupplierNames();

        if (names.isEmpty()) {
            throw new NoSuchElementException("No suppliers found");
        }

        return names;
    }

    @Override
    public List<String> getAllItemsName() {
        List<String> names = itemRepo.findAllItemNames();

        if(names.isEmpty()) {
            throw new NoSuchElementException("No suppliers found");
        }

        return names;
    }



}
