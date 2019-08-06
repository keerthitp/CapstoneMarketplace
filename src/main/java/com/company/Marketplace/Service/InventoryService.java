package com.company.Marketplace.Service;

import com.company.Marketplace.DTO.Inventory;
import com.company.Marketplace.DTO.Product;

import java.util.List;

public interface InventoryService {

    Inventory addProductToInventory(Product product, Integer quantity);
    List<Inventory> getAllInventoryItemsFromDatabase();
    Inventory updateQuantityInInventory(Integer id, Integer quantity);

}
