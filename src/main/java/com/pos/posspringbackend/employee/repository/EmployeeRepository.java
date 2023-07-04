package com.pos.posspringbackend.employee.repository;

import com.pos.posspringbackend.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
