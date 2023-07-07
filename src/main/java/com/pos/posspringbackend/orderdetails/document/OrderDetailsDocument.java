package com.pos.posspringbackend.orderdetails.document;

import com.pos.posspringbackend.order.document.OrderDocument;
import com.pos.posspringbackend.orderdetails.id.OrderDetailsId;
import com.pos.posspringbackend.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="order_details")
@IdClass(OrderDetailsId.class)
public class OrderDetailsDocument {
    @Id
    @Column(name="orderID")
    private Integer orderId;
    @Id
    @Column(name="prodID")
    private Integer productId;
    @Column(name="unitPrice")
    private Double unitPrice;
    private Integer quantity;
    private Double discount;
}
