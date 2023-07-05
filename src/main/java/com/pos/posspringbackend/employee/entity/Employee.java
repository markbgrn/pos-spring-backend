package com.pos.posspringbackend.employee.entity;

import com.pos.posspringbackend.user.enumerated.Role;
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
@Table(name ="tbl_employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employeeID")
    private Integer employeeId;
    @Column(name="fname")
    private String firstName;
    @Column(name="lname")
    private String lastName;
    private String address;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role userType;
}
