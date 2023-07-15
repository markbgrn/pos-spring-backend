package com.pos.posspringbackend.customer.service.impl;

import com.pos.posspringbackend.customer.entity.Customer;
import com.pos.posspringbackend.customer.repository.CustomerRepository;
import com.pos.posspringbackend.customer.service.CustomerService;
import com.pos.posspringbackend.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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
        customer.setCreatedDate(LocalDate.now());

        if (customer.getAge() < 18) {
            throw new IllegalArgumentException("Customer must be 18 years or older to qualify for credit.");
        }

        BigDecimal creditedAmount = customer.getCreditedAmount();
        if (creditedAmount != null && creditedAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("The credited amount cannot be negative.");
        }

        LocalDate dueDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        customer.setDueDate(dueDate);

        customer.setStatus(false);

        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + id + " not found."));
    }

    @Override
    public Customer updateCustomer(Integer id, Customer customer) {
        Customer savedCustomer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + id + " not found."));
        if (customer.getAge() < 18) {
            throw new IllegalArgumentException("Customer must be 18 years or older to qualify for credit.");
        }
        savedCustomer.setFirstName(customer.getFirstName());
        savedCustomer.setLastName(customer.getLastName());
        savedCustomer.setAddress(customer.getAddress());
        savedCustomer.setContact(customer.getContact());
        savedCustomer.setAge(customer.getAge());
        savedCustomer.setStatus(customer.getStatus());
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
