package com.example.api;

import com.example.domain.CustomerFirstname;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customersfirstname")
public class CustomerFirstnameController {
    @Autowired
    CustomerService customerService;

    @GetMapping(path = "{id}")
    public CustomerFirstname getCostomerFirstname(@PathVariable Integer id){
        return customerService.findOneForFirstName(id);
    }
}
