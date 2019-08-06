package com.company.Marketplace.Controller;

import com.company.Marketplace.DTO.Customer;
import com.company.Marketplace.Service.CustomerServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    CustomerServiceImplementation customerService;

    @PostMapping
    public Customer addCustomer(@RequestBody @Valid Customer customer){

        return customerService.addCustomer(customer);


    }

    @GetMapping
    public List<Customer> getAllCustomersFromService(){
        return customerService.getAllCustomers();

    }

    @GetMapping(value = "/email/{emailId}")
    public Customer getCustomerByEmailId(@PathVariable String emailId) throws  Exception{
            return customerService.getCustomerByEmailId(emailId);
    }


    @GetMapping(value = "/id/{id}")
    public Customer getCustomerById(@PathVariable Integer id) throws Exception{
        return customerService.getCustomerById(id);
    }




}
