package com.company.Marketplace.Service;

import com.company.Marketplace.DAO.OrderStatusRepository;
import com.company.Marketplace.DTO.OrderStatus;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderStatusServiceImplementation implements OrderStatusService {

    @Autowired
    OrderStatusRepository orderStatusRepo;

    @Override
    public OrderStatus addOrderStatusToRepo(String orderStatusName) {

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderStatusName(orderStatusName);

        orderStatusRepo.save(orderStatus);

        return orderStatus;

    }

    @Override
    public List<OrderStatus> getAllOrderStatusFromRepo() {
        return orderStatusRepo.findAll();

    }

    @Override
    public void deleteOrderStatus(OrderStatus orderStatus) {
        orderStatusRepo.deleteById(orderStatus.getOrderStatusId());


    }


}
