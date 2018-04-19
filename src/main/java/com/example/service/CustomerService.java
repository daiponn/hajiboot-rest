package com.example.service;

import com.example.domain.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> findAll();

    public Customer findOne(Integer id);

    public Customer create(Customer customer);

    public Customer update(Customer customer);

    public void delete(Integer id);

}
