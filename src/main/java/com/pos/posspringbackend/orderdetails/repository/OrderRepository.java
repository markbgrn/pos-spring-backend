package com.pos.posspringbackend.orderdetails.repository;

import com.pos.posspringbackend.orderdetails.document.OrderDetailsDocument;
import com.pos.posspringbackend.orderdetails.id.OrderDetailsId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderDetailsDocument, OrderDetailsId> {
}
