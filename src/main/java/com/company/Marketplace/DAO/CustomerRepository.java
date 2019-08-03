package com.company.Marketplace.DAO;

import com.company.Marketplace.DTO.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
