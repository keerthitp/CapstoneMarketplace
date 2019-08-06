package com.company.Marketplace.ServiceTests;

import com.company.Marketplace.DAO.InventoryRepository;
import com.company.Marketplace.DAO.ProductCategoryRepository;
import com.company.Marketplace.DTO.Inventory;
import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
import com.company.Marketplace.Service.InventoryServiceImplementation;
import net.bytebuddy.asm.Advice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceImplementationTest {

    @Mock
    @Autowired
    InventoryRepository inventoryRepoMock;

    @InjectMocks
    InventoryServiceImplementation inventoryService;

    @Mock
    @Autowired
    ProductCategoryRepository productCategoryRepoMock;



    Product product;
    ProductCategory productCategory, productCategory2;
    Inventory inventory;

    @Before
    public void setup(){

        product = new Product();

        productCategory = new ProductCategory();
        productCategory.setProductCategoryName("Shampoo");

        productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("Soap");

        product.setProductCategory(productCategory);
        product.setProductName("Nivea");
        product.setProductPrice(12.0);

        inventory =new Inventory();

    }

    @Test(expected = IllegalArgumentException.class)
    public void AddToInventoryThrowsException(){

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory);

        product.setProductCategory(productCategory2);

        inventory.setProduct(product);
        inventory.setInventoryId(1);
        inventory.setProductQuantity(100);


        assertTrue(inventoryService.addProductToInventory(product,100).getInventoryId()!=null );


    }
}
