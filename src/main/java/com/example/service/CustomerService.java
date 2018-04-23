package com.example.service;

import com.example.domain.Customer;
import com.example.domain.CustomerFirstname;

import java.util.List;

public interface CustomerService {

    public List<Customer> findAll();

    public Customer findOne(Integer id);

    public Customer create(Customer customer);

    public Customer update(Customer customer);

    public void delete(Integer id);

    //以下お試し
    public CustomerFirstname findOneForFirstName(Integer id);

}
