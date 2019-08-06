package com.company.Marketplace.Service;

import com.company.Marketplace.DAO.InventoryRepository;
import com.company.Marketplace.DAO.ProductCategoryRepository;
import com.company.Marketplace.DTO.Inventory;
import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryServiceImplementation implements InventoryService{

    @Autowired
    InventoryRepository inventoryRepo;

    @Autowired
    ProductCategoryRepository productCategoryRepo;

    public Inventory addProductToInventory(Product product, Integer quantity){

        Inventory inventory = new Inventory();

        boolean found = false;

        for(ProductCategory productCategory : productCategoryRepo.findAll()){
            if (productCategory.getProductCategoryId() == product.getProductCategory().getProductCategoryId()){
                found = true;
                break;
            }
        }

        if(found == true)
        {
            inventory.setProductQuantity(quantity);
            inventory.setProduct(product);
            inventoryRepo.save(inventory);
            return inventory;
        }
        else
            throw new IllegalArgumentException("Product category invalid");

    }

public     List<Inventory> getAllInventoryItemsFromDatabase(){
        return inventoryRepo.findAll();
}


    public  Inventory updateQuantityInInventory(Integer id, Integer quantity){


        boolean found = false;

        Inventory temp = null;

        for( Inventory inventoryTemp: inventoryRepo.findAll() )
        {
                if (id == inventoryTemp.getInventoryId()){
                    found = true;
                    temp = inventoryTemp;
                    break;
                }
        }

        if(found == false){
            throw  new IllegalArgumentException("Id not in d/b, please enter a valid Id");
        }
        else
        {
            temp.setProductQuantity(quantity);
            inventoryRepo.save(temp);

        }
        return temp;
    }

}
