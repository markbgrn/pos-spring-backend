package com.pos.posspringbackend.customer.entity;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    @Column(name="customer_id")
    private Integer customerId;
    @Column(name="first_name", nullable = false, length = 50)
    @NotBlank(message = "First name is required")
    private String firstName;
    @Column(name="last_name", nullable = false, length = 50)
    @NotBlank(message = "Last name is required")
    private String lastName;
    @Column(name="address", nullable = false, length = 100)
    @NotBlank(message = "Address is required")
    private String address;
    @Column(name="contact", nullable = false, length = 12)
    @NotBlank(message = "Contact is required")
    private String contact;
    @Column(name="age", nullable = false, length = 11)
    @NotBlank(message = "Age is required")
    private Integer age;
    @Column(name="credit_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal creditedAmount;
    private Boolean status;
    private LocalDate dueDate;
    private LocalDate createdDate;


}
