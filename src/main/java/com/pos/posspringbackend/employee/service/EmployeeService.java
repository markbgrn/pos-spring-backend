package com.pos.posspringbackend.employee.service;

import com.pos.posspringbackend.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee createEmployee(Employee employee);
    Employee findById(Integer id);
    Employee update(Integer id, Employee employee);
    boolean delete(Integer id);
}
