package com.pos.posspringbackend.invoice.document;

import com.pos.posspringbackend.customer.entity.Customer;
import com.pos.posspringbackend.employee.entity.Employee;
import com.pos.posspringbackend.order.document.OrderDocument;
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
@Document(collection="invoices")
public class InvoiceDocument {
    @Id
    @Column(name="invoiceID")
    private Integer invoiceId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="orderID")
    private OrderDocument order;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customerID")
    private Customer customer;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="employeeID")
    private Employee employee;
    @Column(name="orderDate")
    private Date orderDate;
    @Column(name="invoiceDate")
    private Date invoiceDate;
}
