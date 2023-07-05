package com.pos.posspringbackend.order.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="orders")
public class OrderDocument {
    @Id
    @Column(name="orderID")
    private Integer orderId;
    @Column(name="customerID")
    private Integer customerId;
    @Column(name="employeeID")
    private Integer employeeId;
    @Column(name="orderdate")
    private Date orderDate;
}
