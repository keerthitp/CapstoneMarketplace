package com.company.Marketplace.ControllerTests;
import com.company.Marketplace.Controller.InventoryController;
import com.company.Marketplace.Controller.ProductController;
import com.company.Marketplace.DTO.Inventory;
import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
import com.company.Marketplace.Service.InventoryServiceImplementation;
import com.company.Marketplace.Service.ProductServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public  class InventoryControllerTest{

    MockMvc mockMvc;

    @Mock
            @Autowired
    InventoryServiceImplementation inventoryService;

    @InjectMocks
    InventoryController inventoryController;


    Product product1, product2, product3, product4;
    ProductCategory productCategory1, productCategory2, productCategory3;

    Set<Product> productSet1, productSet2;

    Inventory inventory1, inventory2, inventory3, inventory4;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(inventoryController)

                .build();

        productCategory1 = new ProductCategory();
        productCategory2 = new ProductCategory();
        productCategory3 = new ProductCategory();

        productCategory1.setProductCategoryName("Shoes");
        productCategory1.setProductCategoryId(1);
        productCategory2.setProductCategoryName("Shirts");
        productCategory2.setProductCategoryId(2);
        productCategory3.setProductCategoryName("Toys");
        productCategory3.setProductCategoryId(3);


        product1 = new Product();
        product2 = new Product();
        product3 = new Product();
        product4 = new Product();

        product1.setProductCategory(productCategory1);
        product1.setProductName("Skechers");
        product1.setProductPrice(90.0);
        product1.setProductRating(4);
        product1.setQuantity(5);

        product2.setProductCategory(productCategory1);
        product2.setProductName("Nike");
        product2.setProductPrice(65.5);
        product2.setProductRating(3);
        product2.setQuantity(10);

        product4.setProductCategory(productCategory1);
        product4.setProductName("Reebok");
        product4.setProductPrice(85.5);
        product4.setProductRating(5);
        product4.setQuantity(10);


        product3.setProductCategory(productCategory2);
        product3.setProductName("Express");
        product3.setProductPrice(45.4);
        product3.setProductRating(5);
        product3.setQuantity(12);

        productSet1 = new HashSet<>();
        productSet1.add(product1);
        productSet1.add(product2);
        productSet1.add(product4);

        productCategory1.setProducts(productSet1);

        productSet2 = new HashSet<>();
        productSet2.add(product3);

        productCategory2.setProducts(productSet2);

        inventory1 = new Inventory();
        inventory2 = new Inventory();
        inventory3 = new Inventory();
        inventory4 = new Inventory();

        inventory1.setProduct(product1);
        inventory1.setProductQuantity(20);

        inventory2.setProduct(product2);
        inventory2.setProductQuantity(10);

        inventory3.setProduct(product3);
        inventory3.setProductQuantity(15);

        inventory4.setProduct(product4);
        inventory4.setProductQuantity(8);

    }


    @Test
    public void shouldGetAllProductsFromInventory() throws  Exception{

        List<Inventory> inventoryList = new ArrayList<>();

        inventoryList.add(inventory1);
        inventoryList.add(inventory2);
        inventoryList.add(inventory3);
        inventoryList.add(inventory4);

        when(inventoryService.getAllInventoryItemsFromDatabase()).thenReturn(inventoryList);

        mockMvc.perform(get("/inventory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].productQuantity",
                        is(inventoryList.get(0).getProductQuantity())));
        verify(inventoryService).getAllInventoryItemsFromDatabase();


    }

}
