package com.pos.posspringbackend.customer.entity;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="tbl_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customerID")
    private Integer customerId;
    @Column(name="fname")
    private String firstName;
    @Column(name="lname")
    private String lastName;
    private String address;
    private String contact;
    private Integer age;
}
