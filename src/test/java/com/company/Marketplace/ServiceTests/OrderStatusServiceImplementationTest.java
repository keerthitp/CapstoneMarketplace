package com.company.Marketplace.ServiceTests;

import com.company.Marketplace.DAO.OrderStatusRepository;
import com.company.Marketplace.DTO.OrderStatus;
import com.company.Marketplace.Service.OrderHistoryServiceImplementation;
import com.company.Marketplace.Service.OrderStatusServiceImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderStatusServiceImplementationTest {

    @Mock
    @Autowired
    OrderStatusRepository orderStatusRepo;

    @InjectMocks
    OrderStatusServiceImplementation orderStatusService;

    OrderStatus orderStatus1, orderStatus2, orderStatus3;
    @Before
    public void setup(){

        orderStatus1 = new OrderStatus();
        orderStatus2 = new OrderStatus();
        orderStatus3 = new OrderStatus();

        orderStatus1.setOrderStatusName("In Progress");
        orderStatus2.setOrderStatusName("Place Order");
        orderStatus3.setOrderStatusName("Ordered");


    }

    @Test
    public void  shouldGetAllOrderStatuses(){

        List<OrderStatus> expectedOrderStatusList = new ArrayList<>();
        expectedOrderStatusList.add(orderStatus1);
        expectedOrderStatusList.add(orderStatus2);
        expectedOrderStatusList.add(orderStatus3);

        when(orderStatusRepo.findAll()).thenReturn(expectedOrderStatusList);

        assertEquals(expectedOrderStatusList, orderStatusService.getAllOrderStatusFromRepo());


    }
}
