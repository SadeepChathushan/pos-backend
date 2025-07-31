package com.aasait.pos.backend.service;

import com.aasait.pos.backend.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService {
    void saveInvoices(List<InvoiceDTO> invoiceDTOList);
}
