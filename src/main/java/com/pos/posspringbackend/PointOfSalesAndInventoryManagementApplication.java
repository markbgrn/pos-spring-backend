package com.pos.posspringbackend;

import com.pos.posspringbackend.employee.entity.Employee;
import com.pos.posspringbackend.employee.entity.Gender;
import com.pos.posspringbackend.employee.repository.EmployeeRepository;
import com.pos.posspringbackend.user.entity.User;
import com.pos.posspringbackend.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Calendar;

import static com.pos.posspringbackend.user.enumerated.Role.ADMIN;

@SpringBootApplication
public class PointOfSalesAndInventoryManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(PointOfSalesAndInventoryManagementApplication.class, args);
    }
}

