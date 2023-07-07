package com.pos.posspringbackend.orderdetails.service.impl;

import com.pos.posspringbackend.orderdetails.document.OrderDetailsDocument;
import com.pos.posspringbackend.orderdetails.id.OrderDetailsId;
import com.pos.posspringbackend.orderdetails.repository.OrderDetailsRepository;
import com.pos.posspringbackend.orderdetails.service.OrderDetailsService;
import com.pos.posspringbackend.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    @Override
    public List<OrderDetailsDocument> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public OrderDetailsDocument createOrderDetails(OrderDetailsDocument orderDetailsDocument) {
        OrderDetailsDocument savedOrderDetails = OrderDetailsDocument.builder()
                .orderId(orderDetailsDocument.getOrderId())
                .productId(orderDetailsDocument.getProductId())
                .unitPrice(orderDetailsDocument.getUnitPrice())
                .quantity(orderDetailsDocument.getQuantity())
                .discount(orderDetailsDocument.getDiscount())
                .build();
        return orderDetailsRepository.save(savedOrderDetails);
    }

    @Override
    public OrderDetailsDocument findByCompositeId(OrderDetailsId id) {
        Optional<OrderDetailsDocument> orderDetailsDocument = orderDetailsRepository.findById(id);
        return orderDetailsDocument.orElseThrow(() -> new ResourceNotFoundException(
                "Order Details with order id: " + id.getOrderId() +
                        " and product id: " + id.getProductId() + " not found"
        ));
    }

    @Override
    public List<OrderDetailsDocument> findAllByOrderId(Integer orderId) {
        return orderDetailsRepository.findByOrderId(orderId);
    }

    @Override
    public OrderDetailsDocument update(OrderDetailsId id, OrderDetailsDocument orderDetailsDocument) {
        OrderDetailsDocument savedOrderDetails = orderDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order Details with order id: " + id.getOrderId() +
                                " and product id: " + id.getProductId() + " not found"
                ));
        savedOrderDetails.setUnitPrice(orderDetailsDocument.getUnitPrice());
        savedOrderDetails.setQuantity(orderDetailsDocument.getQuantity());
        savedOrderDetails.setDiscount(orderDetailsDocument.getDiscount());
        return orderDetailsRepository.save(savedOrderDetails);
    }

    @Override
    public boolean delete(OrderDetailsId id) {
        if (orderDetailsRepository.existsById(id)) {
            orderDetailsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
