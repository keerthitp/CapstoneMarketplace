package com.company.Marketplace.DaoTests;


import com.company.Marketplace.DAO.InventoryRepository;
import com.company.Marketplace.DAO.ProductCategoryRepository;
import com.company.Marketplace.DAO.ProductRepository;
import com.company.Marketplace.DTO.Inventory;
import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryDaoTest {

    Inventory inventory1, inventory2, inventory3;
    Product product1, product2, product3;
    ProductCategory productCategory1, productCategory2, productCategoryExpected1, productCategoryExpected2;

    @Autowired
    InventoryRepository inventoryRepo;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductCategoryRepository productCategoryRepo;

    @Before
    public void setup(){
        productCategory1 = new ProductCategory();
        productCategory2 = new ProductCategory();

        productCategory1.setProductCategoryName("Shoes");
        productCategory2.setProductCategoryName("Shirts");

        product1 = new Product();
        product2 = new Product();
        product3 = new Product();

        product1.setProductCategory(productCategory1);
        product1.setProductName("Skechers");
        product1.setProductPrice(50.0);
        product1.setProductRating(4);

        product2.setProductCategory(productCategory1);
        product2.setProductName("Nike");
        product2.setProductPrice(65.5);
        product2.setProductRating(3);

        product3.setProductCategory(productCategory2);
        product3.setProductName("Express");
        product3.setProductPrice(45.4);
        product3.setProductRating(5);

        inventory1 = new Inventory();
        inventory2 = new Inventory();
        inventory3 = new Inventory();

        inventory1.setProduct(product1);
        inventory1.setProductQuantity(100);

        inventory2.setProduct(product2);
        inventory2.setProductQuantity(150);

        inventory3.setProduct(product3);
        inventory3.setProductQuantity(45);



    }

    @Test
    @Transactional
    public void  addProductsToInventory()
    {

        productCategoryRepo.save(productCategory1);
        productCategoryRepo.save(productCategory2);

        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);

        inventoryRepo.save(inventory1);
        inventoryRepo.save(inventory2);
        inventoryRepo.save(inventory3);

        Assert.assertNotNull(inventory1.getInventoryId());
        Assert.assertNotNull(inventory2.getInventoryId());
        Assert.assertNotNull(inventory3.getInventoryId());

    }


    @Test
    @Transactional
    public void deleteInventory(){
        productCategoryRepo.save(productCategory1);
        productCategoryRepo.save(productCategory2);

        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);

        inventoryRepo.save(inventory1);
        inventoryRepo.save(inventory2);
        inventoryRepo.save(inventory3);


        inventoryRepo.deleteById(inventory1.getInventoryId());
        Optional<Inventory> inventoryTest = inventoryRepo.findById(inventory1.getInventoryId());

        Assert.assertFalse(inventoryTest.isPresent());

    }
}

