package com.pos.posspringbackend.order.document;

import com.pos.posspringbackend.customer.entity.Customer;
import com.pos.posspringbackend.employee.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
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
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customerID")
    private Customer customer;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="employeeID")
    private Employee employee;
    @Column(name="orderdate")
    private Date orderDate;
}
