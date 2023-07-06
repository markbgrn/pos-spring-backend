package com.pos.posspringbackend.invoice.repository;

import com.pos.posspringbackend.invoice.document.InvoiceDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceDocument, Integer> {
}
