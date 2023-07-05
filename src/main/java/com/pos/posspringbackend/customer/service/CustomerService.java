package com.pos.posspringbackend.customer.service;

import com.pos.posspringbackend.customer.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer createCustomer(Customer customer);
    Customer findById(Integer id);
    Customer updateCustomer(Integer id, Customer customer);
    boolean deleteCustomer(Integer id);
}
