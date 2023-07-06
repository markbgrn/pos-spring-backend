package com.pos.posspringbackend.invoice.controller;

import com.pos.posspringbackend.invoice.document.InvoiceDocument;
import com.pos.posspringbackend.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoice")
@CrossOrigin(origins = "localhost:4200")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<InvoiceDocument>> getAllInvoices() {
        List<InvoiceDocument> invoiceDocuments = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoiceDocuments, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<InvoiceDocument> create(@RequestBody InvoiceDocument invoice) {
        InvoiceDocument savedInvoice = invoiceService.createInvoice(invoice);
        return new ResponseEntity<>(savedInvoice, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDocument> findById(@PathVariable Integer id) {
        InvoiceDocument invoiceDocument = invoiceService.findById(id);
        return new ResponseEntity<>(invoiceDocument, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDocument> update(
            @PathVariable Integer id, @RequestBody InvoiceDocument invoice
    ) {
        InvoiceDocument updatedInvoice = invoiceService.update(id, invoice);
        return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        boolean isDeleted = invoiceService.delete(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }
}
