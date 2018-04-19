package com.example.service;

import com.example.domain.Customer;
import com.example.exception.CustomerNotfoundException;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findOne(Integer id){
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotfoundException(id));
    }

    public Customer create(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer){
        return customerRepository.save(customer);
    }

    public void delete(Integer id){
        customerRepository.deleteById(id);
    }

}
