package com.company.Marketplace.Service;

import com.company.Marketplace.DTO.OrderStatus;

import java.util.List;

public interface OrderStatusService {

    OrderStatus addOrderStatusToRepo(String orderStatusName);
    List<OrderStatus> getAllOrderStatusFromRepo();
    void deleteOrderStatus(OrderStatus orderStatus);


}
