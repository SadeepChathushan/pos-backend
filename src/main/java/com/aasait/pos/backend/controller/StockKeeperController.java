
// package com.aasait.pos.backend.controller;

// import com.aasait.pos.backend.dto.ApiResponse;
// import com.aasait.pos.backend.dto.request.AddItemDTO;
// import com.aasait.pos.backend.service.StockkeeperService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.time.LocalDateTime;
// import java.util.List;

// @RestController
// @RequestMapping("api/v1/stokkeeper")
// @CrossOrigin
// public class StockKeeperController {

//     @Autowired
//     private StockkeeperService stockkeeperService;

//     @PostMapping(path = "/add-item")
//     public ResponseEntity<ApiResponse<List<String>>> addItem(@RequestBody AddItemDTO addItemDTO){
//         try {
//             stockkeeperService.saveItem(supplierOrderDTO);

//             ApiResponse<List<String>> response = new ApiResponse<>(
//                     LocalDateTime.now(),
//                     "Order saved Successfully",
//                     List.of("success"),
//                     HttpStatus.CREATED.value()
//             );
//             return new ResponseEntity<>(response, HttpStatus.CREATED);
//         } catch (Exception e){
//             ApiResponse<List<String>> response = new ApiResponse<>(

//                     LocalDateTime.now(),
//                     "Failed to save order" + e.getMessage(),
//                     null,
//                     HttpStatus.INTERNAL_SERVER_ERROR.value()
//             );
//             return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//         }
//     }
//     }

// }
// //comment ekk demma nikn
