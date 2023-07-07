package com.pos.posspringbackend.orderdetails.service;

import com.pos.posspringbackend.orderdetails.document.OrderDetailsDocument;
import com.pos.posspringbackend.orderdetails.id.OrderDetailsId;

import java.util.List;

public interface OrderDetailsService {
    List<OrderDetailsDocument> getAllOrderDetails();
    OrderDetailsDocument createOrderDetails (OrderDetailsDocument orderDetailsDocument);
    OrderDetailsDocument findByCompositeId(OrderDetailsId id);
    List<OrderDetailsDocument> findAllByOrderId(Integer orderId);
    OrderDetailsDocument update(OrderDetailsId id, OrderDetailsDocument orderDetailsDocument);
    boolean delete(OrderDetailsId id);
}
