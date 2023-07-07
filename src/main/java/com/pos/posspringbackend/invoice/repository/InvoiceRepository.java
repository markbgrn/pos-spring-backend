package com.pos.posspringbackend.invoice.repository;

import com.pos.posspringbackend.invoice.document.InvoiceDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceRepository extends MongoRepository<InvoiceDocument, Integer> {
}
