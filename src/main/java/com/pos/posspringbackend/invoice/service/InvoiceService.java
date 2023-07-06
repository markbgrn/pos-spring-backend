package com.pos.posspringbackend.invoice.service;

import com.pos.posspringbackend.invoice.document.InvoiceDocument;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDocument> getAllInvoices();
    InvoiceDocument createInvoice(InvoiceDocument invoiceDocument);
    InvoiceDocument findById(Integer id);
    InvoiceDocument update(Integer id, InvoiceDocument invoiceDocument);
    boolean delete(Integer id);
}
