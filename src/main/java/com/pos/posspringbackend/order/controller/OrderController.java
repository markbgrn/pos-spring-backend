package com.pos.posspringbackend.order.controller;

import com.pos.posspringbackend.order.document.OrderDocument;
import com.pos.posspringbackend.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin(origins = "localhost:4200")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDocument>> getAllOrders() {
        List<OrderDocument> orderDocuments = orderService.getAllOrders();
        return new ResponseEntity<>(orderDocuments, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDocument> create(@RequestBody OrderDocument order) {
        OrderDocument savedOrder = orderService.createOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDocument> findById(@PathVariable Integer id) {
        OrderDocument order = orderService.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDocument> update(
            @PathVariable Integer id, @RequestBody OrderDocument order
    ) {
        OrderDocument updatedOrder = orderService.update(id, order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        boolean isDeleted = orderService.delete(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }
}
