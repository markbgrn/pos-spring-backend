package com.pos.posspringbackend.orderdetails.service.impl;

import com.pos.posspringbackend.orderdetails.document.OrderDetailsDocument;
import com.pos.posspringbackend.orderdetails.id.OrderDetailsId;
import com.pos.posspringbackend.orderdetails.repository.OrderDetailsRepository;
import com.pos.posspringbackend.orderdetails.service.OrderDetailsService;
import com.pos.posspringbackend.product.entity.Product;
import com.pos.posspringbackend.product.repository.ProductRepository;
import com.pos.posspringbackend.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final ProductRepository productRepository;
    @Override
    public List<OrderDetailsDocument> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public OrderDetailsDocument createOrderDetails(OrderDetailsDocument orderDetailsDocument) {
        Optional<Product> foundProduct = productRepository.findById(Integer.valueOf(orderDetailsDocument.getProductId()));
        Product product = foundProduct.get();
        product.setQuantity(product.getQuantity() - Integer.parseInt(orderDetailsDocument.getQuantity()));
        productRepository.save(product);
        OrderDetailsDocument savedOrderDetails = OrderDetailsDocument.builder()
                .productId(orderDetailsDocument.getProductId())
                .customerId(orderDetailsDocument.getCustomerId())
                .amount(orderDetailsDocument.getAmount())
                .quantity(orderDetailsDocument.getQuantity())
                .paymentMethod(orderDetailsDocument.getPaymentMethod())
                .build();
        return orderDetailsRepository.save(savedOrderDetails);
    }

    @Override
    public OrderDetailsDocument findByOrderDetailsById(OrderDetailsDocument id) {
    Optional<OrderDetailsDocument> orderDetailsDocument = orderDetailsRepository.findById(String.valueOf(id));
            return orderDetailsDocument.orElseThrow(() -> new ResourceNotFoundException(
                    "Order Details with order id: " + id.getId() +
                            " and product id: " + id.getProductId() + " not found"
            ));
    }

    //
//    @Override
//    public OrderDetailsDocument findByCompositeId(OrderDetailsDocument id) {
//        Optional<OrderDetailsDocument> orderDetailsDocument = orderDetailsRepository.findById(id);
//        return orderDetailsDocument.orElseThrow(() -> new ResourceNotFoundException(
//                "Order Details with order id: " + id.getId() +
//                        " and product id: " + id.getProductId() + " not found"
//        ));
//    }
//
//    @Override
//    public List<OrderDetailsDocument> findAllByOrderId(String id) {
//        return orderDetailsRepository.findByOrderId(id);
//    }
//
////    @Override
////    public OrderDetailsDocument update(OrderDetailsId id, OrderDetailsDocument orderDetailsDocument) {
////        OrderDetailsDocument savedOrderDetails = orderDetailsRepository.findById(id)
////                .orElseThrow(() -> new ResourceNotFoundException(
////                        "Order Details with order id: " + id.getOrderId() +
////                                " and product id: " + id.getProductId() + " not found"
////                ));
////        savedOrderDetails.setQuantity(orderDetailsDocument.getQuantity());
////        return orderDetailsRepository.save(savedOrderDetails);
////    }
//
//    @Override
//    public boolean delete(OrderDetailsId id) {
//        if (orderDetailsRepository.existsById(id)) {
//            orderDetailsRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
}
