package com.pos.posspringbackend.invoice.service.impl;

import com.pos.posspringbackend.invoice.document.InvoiceDocument;
import com.pos.posspringbackend.invoice.repository.InvoiceRepository;
import com.pos.posspringbackend.invoice.service.InvoiceService;
import com.pos.posspringbackend.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceDocument> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public InvoiceDocument createInvoice(InvoiceDocument invoiceDocument) {
        InvoiceDocument savedInvoice = InvoiceDocument.builder()
                .invoiceId(invoiceDocument.getInvoiceId())
                .order(invoiceDocument.getOrder())
                .customer(invoiceDocument.getCustomer())
                .employee(invoiceDocument.getEmployee())
                .orderDate(invoiceDocument.getOrderDate())
                .invoiceDate(invoiceDocument.getInvoiceDate())
                .build();
        return invoiceRepository.save(savedInvoice);
    }

    @Override
    public InvoiceDocument findById(Integer id) {
        Optional<InvoiceDocument> savedInvoice = invoiceRepository.findById(id);
        return savedInvoice.orElseThrow(() -> new ResourceNotFoundException(
                "Invoice with id: " + id + " not found"
        ));
    }

    @Override
    public InvoiceDocument update(Integer id, InvoiceDocument invoiceDocument) {
        InvoiceDocument savedInvoice = invoiceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Invoice with id: " + id + " not found"
                )
        );
        savedInvoice.setOrder(invoiceDocument.getOrder());
        savedInvoice.setCustomer(invoiceDocument.getCustomer());
        savedInvoice.setEmployee(invoiceDocument.getEmployee());
        savedInvoice.setOrderDate(invoiceDocument.getOrderDate());
        savedInvoice.setInvoiceDate(invoiceDocument.getInvoiceDate());
        return invoiceRepository.save(savedInvoice);
    }

    @Override
    public boolean delete(Integer id) {
        if (invoiceRepository.existsById(id)) {
            invoiceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
