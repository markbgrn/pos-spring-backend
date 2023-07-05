package com.pos.posspringbackend.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String fName;
    @Column(name="lname")
    private String lName;
    private String address;
    private String contact;
    private Integer age;
}
