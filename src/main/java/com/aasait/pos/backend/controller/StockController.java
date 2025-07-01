package com.aasait.pos.backend.controller;

import com.aasait.pos.backend.dto.request.AddSupplierDTO;
import com.aasait.pos.backend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/stockkeeper")
@CrossOrigin
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping(path = "/add-supplier")
    public ResponseEntity<AddSupplierDTO> registerSupplier(@RequestBody AddSupplierDTO addSupplierDTO){
        return ResponseEntity.ok(stockService.registerSupplier(addSupplierDTO));
    }
}
