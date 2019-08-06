package com.company.Marketplace.Service;

import com.company.Marketplace.DAO.CustomerRepository;
import com.company.Marketplace.DTO.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerServiceImplementation implements CustomerService {


    @Autowired
    CustomerRepository customerRepo;

    @Override
    public List<Customer> getAllCustomers() {

        return customerRepo.findAll();
    }

    @Override
    public Customer getCustomerById(Integer customerId) {


        Customer customer = customerRepo.findCustomerByCustomerId(customerId);
        if(customer == null)
            throw new IllegalArgumentException("Customer ID not in the database, please register");
        return customer;
    }

    @Override
    public Customer addCustomer(Customer customer){
        return customerRepo.save(customer);
    }

    @Override
    public  Customer getCustomerByEmailId(String emailId){

        Customer customer = customerRepo.findCustomerBycustomerEmailId(emailId);
        if(customer == null)
            throw new IllegalArgumentException("Customer EmailId not in the database, please register");

        return customer;

    }

}





