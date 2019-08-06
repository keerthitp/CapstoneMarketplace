package com.company.Marketplace.ControllerTests;
import com.company.Marketplace.Controller.CustomerController;
import com.company.Marketplace.DTO.Customer;
import com.company.Marketplace.Service.CustomerServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    @Autowired
    CustomerServiceImplementation customerService;

    @InjectMocks
    CustomerController customerController;

    Customer customer1, customer2, customer3;
    List<Customer> customerList;

    @Before
    public void setup() throws Exception{

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        customer1 = new Customer();
        customer2 = new Customer();

        customer1.setCustomerName("Sandy");
        customer1.setCustomerAddress("12 Main st, Plano, TX");
        customer1.setCustomerEmailId("Sandy@gmail.com");;


        customer2.setCustomerName("Barry");
        customer2.setCustomerAddress("24 Preston Rd, Frisco, TX");
        customer2.setCustomerEmailId("Barry@gmail.com");

        customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
    }

    @Test
    public void shouldGetAllCustomers() throws  Exception{

        when(customerService.getAllCustomers()).thenReturn(customerList);

        mockMvc.perform(get("/customer"))
            .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].customerId", is(customerList.get(0).getCustomerId())));
        verify(customerService).getAllCustomers();
    }
//    @Test
//    public void shouldGetCustomerByEmailId() throws  Exception {
//
//        when(customerService.getCustomerByEmailId(customer2.getCustomerEmailId())).thenReturn(customer2);
//        //http://localhost:8080/customer/email/bary@gm.com
//        mockMvc.perform(get("/customer/email/"+customer2.getCustomerEmailId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$",hasSize(1)))
//                .andExpect(jsonPath("$[0].customerName", is(customer2.getCustomerName())));
//        verify(customerService).getCustomerByEmailId(customer2.getCustomerEmailId());
//    }
}
