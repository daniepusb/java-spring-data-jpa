package com.pdaniel.pizza.service;

import com.pdaniel.pizza.persistence.entity.Customer;
import com.pdaniel.pizza.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findByPhone(String phone){
        return this.customerRepository.findByPhone(phone);
    }
}
