package com.pos.posspringbackend.customer.service.impl;

import com.pos.posspringbackend.customer.entity.Customer;
import com.pos.posspringbackend.customer.repository.CustomerRepository;
import com.pos.posspringbackend.customer.service.CustomerService;
import com.pos.posspringbackend.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        Customer savedCustomer = Customer.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .address(customer.getAddress())
                .contact(customer.getContact())
                .age(customer.getAge())
                .build();
        return customerRepository.save(savedCustomer);
    }

    @Override
    public Customer findById(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + id + " not found."));
    }

    @Override
    public Customer updateCustomer(Integer id, Customer customer) {
        Customer savedCustomer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + id + " not found."));
        savedCustomer.setFirstName(customer.getFirstName());
        savedCustomer.setLastName(customer.getLastName());
        savedCustomer.setAddress(customer.getAddress());
        savedCustomer.setContact(customer.getContact());
        savedCustomer.setAge(customer.getAge());
        return customerRepository.save(savedCustomer);
    }

    @Override
    public boolean deleteCustomer(Integer id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
