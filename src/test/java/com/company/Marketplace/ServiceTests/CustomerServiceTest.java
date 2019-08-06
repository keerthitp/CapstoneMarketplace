package com.company.Marketplace.ServiceTests;

import com.company.Marketplace.DAO.CustomerRepository;
import com.company.Marketplace.DTO.Customer;
import com.company.Marketplace.Service.CustomerServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    @Autowired
    CustomerRepository customerMockRepo;

    @InjectMocks
    CustomerServiceImplementation customerService;


    Customer customer1, customer2, customerExpected1, CustomerExpected2;
    List<Customer> customerList;

    @Before
    public void setup(){

        customerList = new ArrayList<>();

        customer1 = new Customer();
        customer2 = new Customer();

        customer1.setCustomerName("Sandy");
        customer1.setCustomerAddress("12 Main st, Plano, TX");
        customer1.setCustomerEmailId("Sandy@gmail.com");;


        customer2.setCustomerName("Barry");
        customer2.setCustomerAddress("24 Preston Rd, Frisco, TX");
        customer2.setCustomerEmailId("Barry@gmail.com");

        customerList = Arrays.asList(customer1, customer2);
    }

    @Test
    public void shouldGetAllCustomer(){

        List<Customer> expectedList = Arrays.asList(customer1, customer2);
        when(customerMockRepo.findAll()).thenReturn(customerList);
        assertEquals(expectedList, customerService.getAllCustomers());
    }

    @Test
    public  void shouldGetCustomerByCustomerId(){

        Customer ecpectedCustomer = customer2;
        when(customerMockRepo.findCustomerByCustomerId(customer2.getCustomerId()))
                .thenReturn(customer2);

        assertEquals(customer2, customerService.getCustomerById(customer2.getCustomerId()));
    }


}
