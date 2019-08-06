package com.company.Marketplace.Service;

import com.company.Marketplace.DAO.CustomerRepository;
import com.company.Marketplace.DTO.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


public interface CustomerService {


    Customer getCustomerById(Integer customerId);
    Customer getCustomerByEmailId(String emailId);
    List<Customer> getAllCustomers();
    Customer addCustomer(Customer customer);


}
