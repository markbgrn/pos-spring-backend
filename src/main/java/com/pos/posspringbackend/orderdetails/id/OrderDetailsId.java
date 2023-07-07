package com.pos.posspringbackend.orderdetails.id;

import com.pos.posspringbackend.order.document.OrderDocument;
import com.pos.posspringbackend.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderDetailsId implements Serializable {
    private OrderDocument order;
    private Product product;
}
