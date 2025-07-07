package com.aasait.pos.backend.controller;

import com.aasait.pos.backend.dto.ApiResponse;
import com.aasait.pos.backend.dto.request.AddItemDTO;
import com.aasait.pos.backend.dto.request.AddSupplierDTO;
import com.aasait.pos.backend.dto.request.SupplierOrderDTO;
import com.aasait.pos.backend.dto.response.SupplierDTO;
import com.aasait.pos.backend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

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

    @GetMapping(path = "/get-suppliersName")
    public ResponseEntity<ApiResponse<List<String>>> getAllSupplierNames() {
        try {
            List<String> supplierNames = stockService.getAllSupplierNames();

            ApiResponse<List<String>> response = new ApiResponse<>(
                    LocalDateTime.now(),
                    "Supplier names fetched successfully",
                    supplierNames,
                    HttpStatus.OK.value()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            ApiResponse<List<String>> response = new ApiResponse<>(
                    LocalDateTime.now(),
                    e.getMessage(),
                    null,
                    HttpStatus.NOT_FOUND.value()
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponse<List<String>> response = new ApiResponse<>(
                    LocalDateTime.now(),
                    "Internal server error",
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path = "/get-itemNames")
    public ResponseEntity<ApiResponse<List<String>>> getAllItemsName() {
        try {
            List<String> itemNames = stockService.getAllItemsName();

            ApiResponse<List<String>> response = new ApiResponse<>(
                    LocalDateTime.now(),
                    "Supplier names fetched successfully",
                    itemNames,
                    HttpStatus.OK.value()
            );
//
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            ApiResponse<List<String>> response = new ApiResponse<>(
                    LocalDateTime.now(),
                    e.getMessage(),
                    null,
                    HttpStatus.NOT_FOUND.value()
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponse<List<String>> response = new ApiResponse<>(
                    LocalDateTime.now(),
                    "Internal server error",
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(path = "/add-supplier-order")
    public ResponseEntity<ApiResponse<List<String>>> addSupplierOrder(@RequestBody SupplierOrderDTO supplierOrderDTO){
        try {
            stockService.saveSupplierOrder(supplierOrderDTO);

            ApiResponse<List<String>> response = new ApiResponse<>(
                    LocalDateTime.now(),
                    "Order saved Successfully",
                    List.of("success"),
                    HttpStatus.CREATED.value()
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            ApiResponse<List<String>> response = new ApiResponse<>(

                    LocalDateTime.now(),
                    "Failed to save order" + e.getMessage(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
