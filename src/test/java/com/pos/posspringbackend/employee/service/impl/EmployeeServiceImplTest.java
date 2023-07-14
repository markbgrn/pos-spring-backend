package com.pos.posspringbackend.employee.service.impl;

import com.pos.posspringbackend.employee.entity.Employee;
import com.pos.posspringbackend.employee.repository.EmployeeRepository;
import com.pos.posspringbackend.user.enumerated.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    void itShouldFindAllEmployeesWithAccounts() {
        // given
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .build();
        Employee employee1 = Employee.builder()
                .firstName("Jane")
                .lastName("Doe")
                .address("123 Main St")
                .build();
        Employee employee2 = Employee.builder()
                .firstName("John")
                .lastName("Smith")
                .address("123 Main St")
                .build();
        // when
        List<Employee> employees = List.of(employee, employee1, employee2);
        when(employeeRepository.findAll()).thenReturn(employees);
        // then
        List<Employee> employeesFromService = employeeServiceImpl.getAllEmployees();
        assertEquals(3, employeesFromService.size());
        assertEquals("John", employeesFromService.get(0).getFirstName());    // check if the first element is the same as the first element of the list
        assertEquals("Doe", employeesFromService.get(0).getLastName());  // check if the first element is the same as the first element of the list
        assertEquals("123 Main St", employeesFromService.get(0).getAddress());  // check if the first element is the same as the first element of the list
        assertEquals("Jane", employeesFromService.get(1).getFirstName());    // check if the first element is the same as the first element of the list
        assertEquals("Doe", employeesFromService.get(1).getLastName());  // check if the first element is the same as the first element of the list
        assertEquals("123 Main St", employeesFromService.get(1).getAddress());  // check if the first element is the same as the first element of the list
        assertEquals("John", employeesFromService.get(2).getFirstName());    // check if the first element is the same as the first element of the list
        assertEquals("Smith", employeesFromService.get(2).getLastName());  // check if the first element is the same as the first element of the list
        assertEquals("123 Main St", employeesFromService.get(2).getAddress());  // check if the first element is the same as the first element of the list
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void itShouldCreateEmployeeWithAccount() {
        // given
        Employee employee = Employee.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .build();
        // when
        when(employeeRepository.save(employee)).thenReturn(employee);
        // then
        Employee employeeFromService = employeeServiceImpl.createEmployee(employee);
        assertEquals("John", employeeFromService.getFirstName());    // check if the first element is the same as the first element of the list
        assertEquals("Doe", employeeFromService.getLastName());  // check if the first element is the same as the first element of the list
        assertEquals("123 Main St", employeeFromService.getAddress());  // check if the first element is the same as the first element of the list

        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void itShouldFindEmployeeById() {
        // given
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .build();
        // when
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        // then
        Employee employeeFromService = employeeServiceImpl.findById(1);
        assertEquals("John", employeeFromService.getFirstName());    // check if the first element is the same as the first element of the list
        assertEquals("Doe", employeeFromService.getLastName());  // check if the first element is the same as the first element of the list
        assertEquals("123 Main St", employeeFromService.getAddress());  // check if the first element is the same as the first element of the list

        verify(employeeRepository, times(1)).findById(1);
    }

    @Test
    void itShouldUpdateEmployeeDetails() {
        //given
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .build();
        Employee updatedEmployee = Employee.builder()
                .firstName("Jane")
                .lastName("Doe")
                .address("123 Main St")
                .build();
        //when
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(updatedEmployee)).thenReturn(updatedEmployee);
        //then
        Employee employeeFromService = employeeServiceImpl.update(1 ,updatedEmployee);
        assertEquals("Jane", employeeFromService.getFirstName());    // check if the first element is the same as the first element of the list
        assertEquals("Doe", employeeFromService.getLastName());  // check if the first element is the same as the first element of the list
        assertEquals("123 Main St", employeeFromService.getAddress());  // check if the first element is the same as the first element of the list

        verify(employeeRepository, times(1)).findById(1);
        verify(employeeRepository, times(1)).save(updatedEmployee);

    }

    @Test
    void itShouldDeleteEmployeeById() {
        // given
        int id = 1;
        //when
        when(employeeRepository.existsById(id)).thenReturn(true);
        //then
        boolean isDeleted = employeeServiceImpl.delete(id);
        assertTrue(isDeleted);
        verify(employeeRepository, times(1)).existsById(id);
        verify(employeeRepository, times(1)).deleteById(id);
    }
    @Test
    void itShouldNotDeleteEmployeeById() {
        // given
        int id = 1;
        //when
        when(employeeRepository.existsById(id)).thenReturn(false);
        //then
        boolean isDeleted = employeeServiceImpl.delete(id);
        assertFalse(isDeleted);
        verify(employeeRepository, times(1)).existsById(id);
        verify(employeeRepository, never()).deleteById(id);
    }
}