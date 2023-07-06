package com.pos.posspringbackend.order.repository;

import com.pos.posspringbackend.order.document.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderDocument, Integer> {
}
