package com.company.Marketplace.DAO;

import com.company.Marketplace.DTO.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {


    Inventory findByProduct(Integer productId);
}
