package com.company.Marketplace.DaoTests;

import com.company.Marketplace.DAO.OrderStatusRepository;
import com.company.Marketplace.DTO.OrderStatus;
import org.apache.catalina.servlets.DefaultServlet;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderStatusDaoTest {

    @Autowired
    OrderStatusRepository orderStatusRepo;

    OrderStatus orderStatus1,orderStatus2, orderStatus3;

    @Before
    public void setup(){

        orderStatusRepo.deleteAll();

        orderStatus1 =new OrderStatus();
        orderStatus2 =new OrderStatus();
        orderStatus3 =new OrderStatus();

        orderStatus1.setOrderStatusName("In Progress");
        orderStatus2.setOrderStatusName("Place order");
        orderStatus3.setOrderStatusName("Ordered");


    }

    @Test
    @Transactional
    public void shouldAddOrderStatus(){

        orderStatusRepo.save(orderStatus1);
        orderStatusRepo.save(orderStatus2);
        orderStatusRepo.save(orderStatus3);

        Assert.assertEquals(3, orderStatusRepo.findAll().size());
    }



    @Test
    @Transactional
    public void shouldDeleteOrderStatus(){

        orderStatusRepo.save(orderStatus1);
        orderStatusRepo.save(orderStatus2);
        orderStatusRepo.save(orderStatus3);

        orderStatusRepo.deleteById(orderStatus3.getOrderStatusId());

        Assert.assertEquals(2, orderStatusRepo.findAll().size());

        Optional<OrderStatus> orderStatusOptional = orderStatusRepo.findById(orderStatus3.getOrderStatusId());
        assertFalse(orderStatusOptional.isPresent());

    }
}
