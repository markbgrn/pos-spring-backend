package com.pos.posspringbackend.employee.service.impl;

import com.pos.posspringbackend.employee.entity.Employee;
import com.pos.posspringbackend.employee.repository.EmployeeRepository;
import com.pos.posspringbackend.employee.service.EmployeeService;
import com.pos.posspringbackend.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Employee createEmployee(Employee employee) {
        Employee savedEmployee = Employee.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .address(employee.getAddress())
                .username(employee.getUsername())
                .password(employee.getPassword())
                .userType(employee.getUserType())
                .build();
        return employeeRepository.save(savedEmployee);
    }

    @Override
    public Employee findById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElseThrow(() -> new ResourceNotFoundException("Employee with id: " + id + " not found"));
    }

    @Override
    public Employee update(Integer id, Employee employee) {
        Employee savedEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with id: " + id + " not found"));
        savedEmployee.setFirstName(employee.getFirstName());
        savedEmployee.setLastName(employee.getLastName());
        savedEmployee.setAddress(employee.getAddress());
        savedEmployee.setUsername(employee.getUsername());
        savedEmployee.setPassword(employee.getPassword());
        savedEmployee.setUserType(employee.getUserType());
        return employeeRepository.save(savedEmployee);
    }

    @Override
    public boolean delete(Integer id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
