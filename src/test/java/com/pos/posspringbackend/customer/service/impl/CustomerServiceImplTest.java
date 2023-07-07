package com.pos.posspringbackend.customer.service.impl;

import com.pos.posspringbackend.customer.entity.Customer;
import com.pos.posspringbackend.customer.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    void it_Should_Find_All_Customer() {
        // given
        Customer customer = Customer.builder()
                .firstName("Sample First Name")
                .lastName("Sample Last Name")
                .address("Sample Address")
                .contact("Sample Contact")
                .age(20)
                .build();
        Customer customer2 = Customer.builder()
                .firstName("Sample First Name 2")
                .lastName("Sample Last Name 2")
                .address("Sample Address 2")
                .contact("Sample Contact 2")
                .age(21)
                .build();
        // when
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer2);
        when(customerRepository.findAll()).thenReturn(customers);
        // then
        List<Customer> customersFromService = customerServiceImpl.getAllCustomers();
        assertEquals(2, customersFromService.size());
        assertEquals("Sample First Name", customersFromService.get(0).getFirstName());    // check if the first element is the same as the first element of the list
        assertEquals("Sample Last Name", customersFromService.get(0).getLastName());  // check if the first element is the same as the first element of the list
        assertEquals("Sample Address", customersFromService.get(0).getAddress());  // check if the first element is the same as the first element of the list
        assertEquals("Sample Contact", customersFromService.get(0).getContact());  // check if the first element is the same as the first element of the list
        assertEquals(20, customersFromService.get(0).getAge());  // check if the first element is the same as the first element of the list
        assertEquals("Sample First Name 2", customersFromService.get(1).getFirstName());  // check if the second element is the same as the second element of the list
        assertEquals("Sample Last Name 2", customersFromService.get(1).getLastName());  // check if the second element is the same as the second element of the list
        assertEquals("Sample Address 2", customersFromService.get(1).getAddress());  // check if the second element is the same as the second element of the list
        assertEquals("Sample Contact 2", customersFromService.get(1).getContact());  // check if the second element is the same as the second element of the list
        assertEquals(21, customersFromService.get(1).getAge());  // check if the second element is the same as the second element of the list

        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void it_Should_Create_A_Customer() {
        // given
        Customer customer = Customer.builder()
                .firstName("Sample First Name")
                .lastName("Sample Last Name")
                .address("Sample Address")
                .contact("09123456789")
                .age(20)
                .build();
        // when
        when(customerRepository.save(customer)).thenReturn(customer);
        // then
        Customer customerFromService = customerServiceImpl.createCustomer(customer);
        assertEquals("Sample First Name", customerFromService.getFirstName());
        assertEquals("Sample Last Name", customerFromService.getLastName());
        assertEquals("Sample Address", customerFromService.getAddress());
        assertEquals("09123456789", customerFromService.getContact());
        assertEquals(20, customerFromService.getAge());

        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void it_Should_Find_A_Customer_By_Id() {
        // given
        Customer customer = Customer.builder()
                .firstName("Sample First Name")
                .lastName("Sample Last Name")
                .address("Sample Address")
                .contact("Sample Contact")
                .age(20)
                .build();
        // when
        when(customerRepository.findById(1)).thenReturn(java.util.Optional.of(customer));
        // then
        Customer customerFromService = customerServiceImpl.findById(1);
        assertEquals("Sample First Name", customerFromService.getFirstName());
        assertEquals("Sample Last Name", customerFromService.getLastName());
        assertEquals("Sample Address", customerFromService.getAddress());
        assertEquals("Sample Contact", customerFromService.getContact());
        assertEquals(20, customerFromService.getAge());

        verify(customerRepository, times(1)).findById(1);
    }

    @Test
    void it_Should_Update_The_Customer() {
        // given
        Customer customer = Customer.builder()
                .firstName("Sample First Name")
                .lastName("Sample Last Name")
                .address("Sample Address")
                .contact("Sample Contact")
                .age(20)
                .build();
        Customer customer2 = Customer.builder()
                .firstName("Sample First Name 2")
                .lastName("Sample Last Name 2")
                .address("Sample Address 2")
                .contact("Sample Contact 2")
                .age(21)
                .build();
        // when
        when(customerRepository.findById(1)).thenReturn(java.util.Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer2);
        // then
        Customer customerFromService = customerServiceImpl.updateCustomer(1, customer2);
        assertEquals("Sample First Name 2", customerFromService.getFirstName());
        assertEquals("Sample Last Name 2", customerFromService.getLastName());
        assertEquals("Sample Address 2", customerFromService.getAddress());
        assertEquals("Sample Contact 2", customerFromService.getContact());
        assertEquals(21, customerFromService.getAge());

        verify(customerRepository, times(1)).findById(1);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void it_Should_Delete_A_Customer_By_Id() {
        // given
        int id = 1;
        // when
        when(customerRepository.existsById(id)).thenReturn(true);
        // then
        boolean isDeleted = customerServiceImpl.deleteCustomer(id);
        assertTrue(isDeleted);

        verify(customerRepository, times(1)).existsById(id);
        verify(customerRepository, times(1)).deleteById(id);
    }
    @Test
    void it_Should_Not_Delete_A_Customer_By_Id() {
        // given
        int id = 1;
        // when
        when(customerRepository.existsById(id)).thenReturn(false);
        // then
        boolean isDeleted = customerServiceImpl.deleteCustomer(id);
        assertFalse(isDeleted);

        verify(customerRepository, times(1)).existsById(id);
        verify(customerRepository, never()).deleteById(id);
    }
}