package com.pos.posspringbackend.orderdetails.document;

import com.pos.posspringbackend.orderdetails.id.OrderDetailsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="order_details")
@IdClass(OrderDetailsId.class)
public class OrderDetailsDocument {
//    @Id
//    @Column(name="orderID")
//    private String orderId;
    @Id
    private String id;
    @Column(name="prodID")
    private String productId;
    @Column(name="customer_id")
    private String customerId;
    @Column(name="amount")
    private String amount;
    private String quantity;
    private String paymentMethod;
//    private Double discount;
}
