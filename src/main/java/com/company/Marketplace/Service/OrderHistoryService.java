package com.company.Marketplace.Service;

import com.company.Marketplace.DTO.Cart;

import java.util.List;

public interface OrderHistoryService  {

    Cart addOrUpdateCartToOrderHistory(Cart cart);
    List<Cart> getAllOrdersFromOrderHistory();
}
