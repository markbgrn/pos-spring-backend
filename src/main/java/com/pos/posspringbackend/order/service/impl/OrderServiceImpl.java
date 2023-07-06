package com.pos.posspringbackend.order.service.impl;

import com.pos.posspringbackend.order.document.OrderDocument;
import com.pos.posspringbackend.order.repository.OrderRepository;
import com.pos.posspringbackend.order.service.OrderService;
import com.pos.posspringbackend.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<OrderDocument> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderDocument createOrder(OrderDocument orderDocument) {
        OrderDocument savedOrder = OrderDocument.builder()
                .orderId(orderDocument.getOrderId())
                .customer(orderDocument.getCustomer())
                .employee(orderDocument.getEmployee())
                .orderDate(orderDocument.getOrderDate())
                .build();
        return orderRepository.save(savedOrder);
    }

    @Override
    public OrderDocument findById(Integer id) {
        Optional<OrderDocument> orderDocument = orderRepository.findById(id);
        return orderDocument.orElseThrow(() -> new ResourceNotFoundException(
                "Order with id: " + id + " not found"
        ));
    }

    @Override
    public OrderDocument update(Integer id, OrderDocument orderDocument) {
        OrderDocument savedOrder = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Order with id: " + id + " not found"
                )
        );
        savedOrder.setCustomer(orderDocument.getCustomer());
        savedOrder.setEmployee(orderDocument.getEmployee());
        savedOrder.setOrderDate(orderDocument.getOrderDate());
        return orderRepository.save(savedOrder);
    }

    @Override
    public boolean delete(Integer id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
