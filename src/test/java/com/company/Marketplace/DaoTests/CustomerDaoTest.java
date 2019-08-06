package com.company.Marketplace.DaoTests;

import com.company.Marketplace.DAO.CustomerRepository;
import com.company.Marketplace.DTO.Customer;
import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerDaoTest {

    @Autowired
    CustomerRepository customerRepo;

    Customer customer1, customer2, customerExpected1, CustomerExpected2;

    @Before
    public void setup(){

        customer1 = new Customer();
        customer2 = new Customer();

        customer1.setCustomerName("Sandy");
        customer1.setCustomerAddress("12 Main st, Plano, TX");
        customer1.setCustomerEmailId("Sandy@gmail.com");;


        customer2.setCustomerName("Barry");
        customer2.setCustomerAddress("24 Preston Rd, Frisco, TX");
        customer2.setCustomerEmailId("Barry@gmail.com");
    }

    @Test
    @Transactional
    public void shouldAddCustomer(){


        customerRepo.save(customer1);
        customerRepo.save(customer2);

        assertNotNull(customer1.getCustomerId());
        assertNotNull(customer2.getCustomerId());

    }

    @Test
    @Transactional
    public void shouldDeleteCustomer(){
        customerRepo.save(customer2);
        customerRepo.save(customer1);

        customerRepo.deleteById(customer2.getCustomerId());

        Optional<Customer> customerOptional = customerRepo.findById(customer2.getCustomerId());
        assertFalse(customerOptional.isPresent());
    }

    @Test
    @Transactional
    public void updateCustomerInfo(){
        customerRepo.save(customer2);
        customerRepo.save(customer1);

        customer2.setCustomerName("Barrystone");
        customerRepo.save(customer2);

        customer1.setCustomerEmailId("Sandy123@gmail.com");
        customerRepo.save(customer1);

        assertEquals("Barrystone", customer2.getCustomerName());
        assertEquals("Sandy123@gmail.com", customer1.getCustomerEmailId());
    }
}
