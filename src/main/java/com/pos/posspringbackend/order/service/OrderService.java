package com.pos.posspringbackend.order.service;

import com.pos.posspringbackend.order.document.OrderDocument;

import java.util.List;

public interface OrderService {
    List<OrderDocument> getAllOrders();
    OrderDocument createOrder(OrderDocument orderDocument);
    OrderDocument findById(Integer id);
    OrderDocument update(Integer id, OrderDocument orderDocument);
    boolean delete(Integer id);
}
