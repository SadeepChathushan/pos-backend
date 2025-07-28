package com.aasait.pos.backend.service;

import com.aasait.pos.backend.dto.request.AddSalesInvoiceDTO;
import com.aasait.pos.backend.dto.response.CashierDTO;
import com.aasait.pos.backend.entity.SalesInvoice;
import com.aasait.pos.backend.dto.response.CustomerDTO;
import java.util.List;
import com.aasait.pos.backend.dto.response.SalesInvoiceDTO;
import java.util.List;

public interface CashierService {
    SalesInvoice createInvoice(AddSalesInvoiceDTO dto);
    List<CashierDTO> getAllItemsForCashier();
    List<CustomerDTO> getAllCustomers();
    SalesInvoiceDTO getSalesInvoiceCountByCashierId(Long cashierId);
    Double getTotalSalesAmountByCashierId(Long cashierId);

}
