package com.pos.posspringbackend.orderdetails.controller;

import com.pos.posspringbackend.orderdetails.document.OrderDetailsDocument;
import com.pos.posspringbackend.orderdetails.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SalesController {
    private final OrderDetailsService orderDetailsService;

    @GetMapping
    public ResponseEntity<List<OrderDetailsDocument>> getAllOrderDetails() {
        List<OrderDetailsDocument> orderDetailsDocuments = orderDetailsService.getAllOrderDetails();
        return new ResponseEntity<>(orderDetailsDocuments, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDetailsDocument> createOrderDetails(@RequestBody OrderDetailsDocument orderDetailsDocument) {
        OrderDetailsDocument savedOrderDetails = orderDetailsService.createOrderDetails(orderDetailsDocument);
        return new ResponseEntity<>(savedOrderDetails, HttpStatus.CREATED);
    }
}
