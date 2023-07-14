package com.pos.posspringbackend.auth.request;

import com.pos.posspringbackend.employee.entity.Employee;
import com.pos.posspringbackend.employee.entity.Gender;
import com.pos.posspringbackend.user.enumerated.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String email;
    private String password;
    private Role role;
    private Employee employee;
}
