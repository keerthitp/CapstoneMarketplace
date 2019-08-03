package com.company.Marketplace.DAO;

import com.company.Marketplace.DTO.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<Cart, Integer> {


}
