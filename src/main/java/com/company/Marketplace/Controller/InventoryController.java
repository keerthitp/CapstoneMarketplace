package com.company.Marketplace.Controller;

import com.company.Marketplace.DTO.Inventory;
import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.Service.InventoryServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {

    @Autowired
    InventoryServiceImplementation inventoryService;

    @GetMapping
    public List<Inventory> getAllInventoryItems(){
        return inventoryService.getAllInventoryItemsFromDatabase();
    }

    @PostMapping(value = "/product/quantity/{quantity}")
    public Inventory addInventory(@RequestBody @Valid Product product, @PathVariable Integer quantity){
        return inventoryService.addProductToInventory(product,quantity);
    }

    @PutMapping(value = "/id/{id}/quantity/{quantity}")
    public Inventory updateInventory(@PathVariable  Integer id, @PathVariable Integer quantity){
        return inventoryService.updateQuantityInInventory(id,quantity);
    }

}
