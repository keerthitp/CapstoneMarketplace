package com.company.Marketplace.DAO;

import com.company.Marketplace.DTO.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    //List<Customer> findAllCustomers();
    Customer findCustomerByCustomerId(Integer customerId);
    Customer findCustomerBycustomerEmailId(String emailId);
}
