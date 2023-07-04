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
    private Integer employeeId;
    private String fName;
    private String lName;
    private String address;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role userType;
}
