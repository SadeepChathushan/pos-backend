package com.aasait.pos.backend.service.impl;

import com.aasait.pos.backend.dto.InvoiceDTO;
import com.aasait.pos.backend.entity.Invoice;
import com.aasait.pos.backend.repository.InvoiceRepository;
import com.aasait.pos.backend.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    public void saveInvoices(List<InvoiceDTO> invoiceDTOList) {
        List<Invoice> invoices = invoiceDTOList.stream()
                .map(dto -> Invoice.builder()
                        .batchId(dto.getBatchId())
                        .itemId(dto.getItemId())
                        .quantity(dto.getQuantity())
                        .saleInvoiceId(dto.getSaleInvoiceId())
                        .build())
                .toList();

        invoiceRepository.saveAll(invoices);
    }
}
