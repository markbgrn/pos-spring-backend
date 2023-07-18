package com.pos.posspringbackend.orderdetails.repository;

import com.pos.posspringbackend.orderdetails.document.OrderDetailsDocument;
import com.pos.posspringbackend.orderdetails.id.OrderDetailsId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderDetailsRepository extends MongoRepository<OrderDetailsDocument, String> {
//    List<OrderDetailsDocument> findByOrderId(Integer id);
}
