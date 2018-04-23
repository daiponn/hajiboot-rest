package com.example.api;

import com.example.domain.Customer;
import com.example.json.CustomerJson;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    //顧客全件取得
    @GetMapping
    List<CustomerJson> getCustomers(){
        List<Customer> customers = customerService.findAll();
        List<CustomerJson> customerJsonList = new ArrayList<>();
        for(Customer customer: customers){
            CustomerJson customerJson = new CustomerJson(
                    customer.getId(),customer.getFirstName(), customer.getLastName());
            customerJsonList.add(customerJson);
        }
        return customerJsonList;
    }

    @GetMapping(path = "{id}")
    CustomerJson getCustomer(@PathVariable Integer id){
        Customer customer = customerService.findOne(id);
        CustomerJson customerJson = new CustomerJson(
                customer.getId(), customer.getFirstName(), customer.getLastName());
        return customerJson;


    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerJson postCustomers(@Validated @RequestBody CustomerJson customerjson){
        Customer customer = new Customer();
        customer.setFirstName(customerjson.getFirstName());
        customer.setLastName(customerjson.getLastName());
        Customer createdCustomer = customerService.create(customer);
        CustomerJson customerJson = new CustomerJson(
                createdCustomer.getId(), createdCustomer.getFirstName(), createdCustomer.getLastName());
        return customerJson;
    }


    @PutMapping(path = "{id}")
    CustomerJson putCustomer(@PathVariable Integer id, @Validated @RequestBody CustomerJson customerjson ){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(customerjson.getFirstName());
        customer.setLastName(customerjson.getLastName());
        Customer updatedCustomer = customerService.update(customer);
        CustomerJson customerJson = new CustomerJson(
                updatedCustomer.getId(), updatedCustomer.getFirstName(), updatedCustomer.getLastName());
        return customerJson;
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@PathVariable Integer id){
        customerService.delete(id);
    }


}
