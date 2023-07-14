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
//        Employee savedEmployee = Employee.builder()
//                .firstName(employee.getFirstName())
//                .lastName(employee.getLastName())
//                .gender(employee.getGender())
//                .dateOfBirth(employee.getDateOfBirth())
//                .phone(employee.getPhone())
//                .address(employee.getAddress())
//                .zipCode(employee.getZipCode())
//                .build();
        return employeeRepository.save(employee);
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
        savedEmployee.setGender(employee.getGender());
        savedEmployee.setDateOfBirth(employee.getDateOfBirth());
        savedEmployee.setPhone(employee.getPhone());
        savedEmployee.setAddress(employee.getAddress());
        savedEmployee.setZipCode(employee.getZipCode());
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
