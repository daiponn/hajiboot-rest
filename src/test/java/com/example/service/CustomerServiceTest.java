package com.example.service;

import static org.junit.Assert.assertEquals;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerServiceImpl customerService;

    @MockBean
    CustomerRepository customerRepository;

    @Test
    public void TestFindAll(){
        Customer customer1 = new Customer(1,"Daisuke","Kasahara");
        Customer customer2 = new Customer(2,"Sayuri","Hukui");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);

        given(this.customerRepository.findAll())
                .willReturn(customerList);
        assertEquals(customerList, customerService.findAll());

    }

}
