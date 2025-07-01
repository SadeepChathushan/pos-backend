package com.aasait.pos.backend.controller;

import com.aasait.pos.backend.dto.ApiResponse;
import com.aasait.pos.backend.dto.request.AddItemDTO;
import com.aasait.pos.backend.dto.request.AddSupplierDTO;
import com.aasait.pos.backend.dto.response.SupplierDTO;
import com.aasait.pos.backend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping(path = "get-all-supplier")
    public List<SupplierDTO> getAllSupplier(){
        return stockService.getAllSupplier();
    }

    @PostMapping(path = "/add-item")
    public ResponseEntity<ApiResponse<AddItemDTO>> addItem(@RequestBody AddItemDTO addItemDTO){
        AddItemDTO savedItem = stockService.addItem(addItemDTO);
        ApiResponse<AddItemDTO> response =new ApiResponse<>(
                LocalDateTime.now(),
                "Item added successfully",
                savedItem,
                HttpStatus.CREATED.value()
        );
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }
}
