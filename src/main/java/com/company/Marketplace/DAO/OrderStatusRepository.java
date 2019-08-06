package com.company.Marketplace.DAO;

import com.company.Marketplace.DTO.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

}
