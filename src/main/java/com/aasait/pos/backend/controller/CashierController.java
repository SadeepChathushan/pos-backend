package com.aasait.pos.backend.controller;

import com.aasait.pos.backend.dto.response.CashierDTO;
import com.aasait.pos.backend.dto.response.CustomerDTO;
import com.aasait.pos.backend.dto.response.SalesInvoiceDTO;
import com.aasait.pos.backend.entity.OurUsers;
import com.aasait.pos.backend.service.CashierService;
import com.aasait.pos.backend.service.JWTUtils;
import com.aasait.pos.backend.repository.OurUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cashier")
public class CashierController {

    @Autowired
    private CashierService cashierService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private OurUsersRepo ourUsersRepo;

    @GetMapping("/items")
    public List<CashierDTO> getAllItems(@RequestHeader("Authorization") String token) {
        return cashierService.getAllItemsForCashier();
    }

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers(@RequestHeader("Authorization") String token) {
        return cashierService.getAllCustomers();
    }

    @GetMapping("/invoice-count")
    public SalesInvoiceDTO getOwnInvoiceCount(@RequestHeader("Authorization") String token) {
        String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;
        String username = jwtUtils.extractUsername(jwt);

        OurUsers user = ourUsersRepo.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + username));

        return cashierService.getSalesInvoiceCountByCashierId((long) user.getId());

    }
    @GetMapping("/invoice-total")
    public Double getOwnTotalSalesAmount(@RequestHeader("Authorization") String token) {
        String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;
        String username = jwtUtils.extractUsername(jwt);

        OurUsers user = ourUsersRepo.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + username));

        return cashierService.getTotalSalesAmountByCashierId((long) user.getId());
    }

}
