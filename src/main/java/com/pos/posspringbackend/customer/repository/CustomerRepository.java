package com.pos.posspringbackend.customer.repository;

import com.pos.posspringbackend.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
