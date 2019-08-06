package com.company.Marketplace.Controller;

import com.company.Marketplace.DTO.OrderStatus;
import com.company.Marketplace.Service.OrderStatusServiceImplementation;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orderstatus")
public class OrderStatusController {

    @Autowired
    OrderStatusServiceImplementation orderStatusService;

    @GetMapping
    public List<OrderStatus> returnAllOrderStatus(){
        return orderStatusService.getAllOrderStatusFromRepo();

    }

    @PostMapping
    public OrderStatus addOrderStatus(@RequestBody String orderStatus){

        return orderStatusService.addOrderStatusToRepo(orderStatus);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteOrderStatus(@RequestBody OrderStatus orderStatus, @PathVariable Integer id){

        if (id != orderStatus.getOrderStatusId())
            throw new IllegalArgumentException("Order status id does not match");

        return "Order Status: "+ orderStatus.getOrderStatusName() + " is deleted";
    }
}
