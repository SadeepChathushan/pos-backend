package com.aasait.pos.backend.service.IMPL;

import com.aasait.pos.backend.dto.request.AddSalesInvoiceDTO;
import com.aasait.pos.backend.dto.response.CashierDTO;
import com.aasait.pos.backend.dto.response.CustomerDTO;
import com.aasait.pos.backend.dto.response.SalesInvoiceDTO;
import com.aasait.pos.backend.entity.Item;
import com.aasait.pos.backend.entity.SalesInvoice;
import com.aasait.pos.backend.repository.CustomerRepo;
import com.aasait.pos.backend.repository.ItemRepo;
import com.aasait.pos.backend.repository.SalesInvoiceRepo;
import com.aasait.pos.backend.service.CashierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CashierServiceImpl implements CashierService {

    @Autowired
    private SalesInvoiceRepo invoiceRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SalesInvoice createInvoice(AddSalesInvoiceDTO dto) {
        // Stubbed implementation - replace with actual logic
        return new SalesInvoice();
    }

    @Override
    public List<CashierDTO> getAllItemsForCashier() {
        List<Item> items = itemRepo.findAll();
        return items.stream()
                .map(item -> {
                    CashierDTO dto = new CashierDTO();
                    dto.setId(item.getId());
                    dto.setName(item.getName());
                    dto.setPrice(item.getPrice());
                    dto.setQuantity(item.getQuantity());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepo.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public SalesInvoiceDTO getSalesInvoiceCountByCashierId(Long cashierId) {
        Long count = invoiceRepo.countByCashier_Id(cashierId);
        return new SalesInvoiceDTO(cashierId, count);
    }
    @Override
    public Double getTotalSalesAmountByCashierId(Long cashierId) {
        return invoiceRepo.getTotalAmountByCashierId(cashierId);
    }
}
