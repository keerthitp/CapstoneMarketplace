package com.company.Marketplace.ControllerTests;

import com.company.Marketplace.Controller.OrderStatusController;
import com.company.Marketplace.DTO.OrderStatus;
import com.company.Marketplace.Service.OrderStatusServiceImplementation;
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

public class OrderStatusControllerTest {

    private MockMvc mockMvc;

    @Mock
    @Autowired
    OrderStatusServiceImplementation orderStatusService;

    @InjectMocks
    OrderStatusController orderStatusController;

    OrderStatus orderStatus1, orderStatus2, orderStatus3;
    List<OrderStatus> orderStatusList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderStatusController).build();
        orderStatusList = new ArrayList<>();

        orderStatus1 = new OrderStatus();
        orderStatus2 = new OrderStatus();
        orderStatus3 = new OrderStatus();

        orderStatus1.setOrderStatusName("In Progress");
        orderStatus2.setOrderStatusName("Place order");
        orderStatus3.setOrderStatusName("Ordered");


    }

    @Test
    public void shouldGetAllOrders() throws Exception {
        orderStatusList.add(orderStatus1);
        orderStatusList.add(orderStatus2);
        orderStatusList.add(orderStatus3);

        when(orderStatusService.getAllOrderStatusFromRepo()).thenReturn(orderStatusList);

        // mockmvc perform might throw exception hence the method says throws Exception
        mockMvc.perform(get("/orderstatus"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].orderStatusId", is(orderStatusList.get(0).getOrderStatusId())));

        verify(orderStatusService).getAllOrderStatusFromRepo();

    }
}
