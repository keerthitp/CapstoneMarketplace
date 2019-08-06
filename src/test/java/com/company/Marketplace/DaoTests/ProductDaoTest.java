package com.company.Marketplace.DaoTests;


import com.company.Marketplace.DAO.InventoryRepository;
import com.company.Marketplace.DAO.ProductCategoryRepository;
import com.company.Marketplace.DAO.ProductRepository;
import com.company.Marketplace.DTO.Inventory;
import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTest {

    Product product1, product2, product3;
    ProductCategory productCategory1, productCategory2, productCategoryExpected1, productCategoryExpected2;

    Set<Product> productSet;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductCategoryRepository productCategoryRepo;

    @Before
    public void setup(){


        productRepo.deleteAll();
        productCategoryRepo.deleteAll();

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
        product1.setQuantity(5);

        product2.setProductCategory(productCategory1);
        product2.setProductName("Nike");
        product2.setProductPrice(65.5);
        product2.setProductRating(3);
        product2.setQuantity(10);

        product3.setProductCategory(productCategory2);
        product3.setProductName("Express");
        product3.setProductPrice(45.4);
        product3.setProductRating(5);
        product3.setQuantity(12);

        productSet = new HashSet<>();
        productSet.add(product1);
        productSet.add(product2);

        productCategory1.setProducts(productSet);

    }

    @Test
    @Transactional
    public void ShouldAddProducts(){
        // tests if a new product can be added

//        productCategoryRepo.save(productCategory1);
        productCategoryRepo.save(productCategory2);

        productRepo.save(product3);

        assertEquals(1, productRepo.findAll().size());
        Assert.assertNotNull(product3.getProductId());

    }

    @Test
    @Transactional
    public void shouldGetProducts(){


        assertEquals(0, productCategoryRepo.findAll().size());
        assertEquals(0, productRepo.findAll().size());


        productCategoryRepo.save(productCategory1);
        productCategoryRepo.save(productCategory2);

        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);

        assertEquals(3, productRepo.findAll().size());

        //Assert.assertEquals(2, productRepo.findProductByProductCategory(productCategory1).size());
//        Assert.assertEquals
//        Assert.assertEquals
//        Assert.assertEquals
//

    }

//
//    @Test
//    @Transactional
//    public void shouldGetProductsByProductCategory(){
//
//
//        assertEquals(0, productCategoryRepo.findAll().size());
//        assertEquals(0, productRepo.findAll().size());
//
//
//        productCategoryRepo.save(productCategory1);
//        productCategoryRepo.save(productCategory2);
//
//        productRepo.save(product1);
//        productRepo.save(product2);
//        productRepo.save(product3);
//
//      //  assertEquals(2, productRepo..getProductsByProductCategory(productCategory1.getProductCategoryName()).size());
////        Assert.assertEquals
////        Assert.assertEquals
////        Assert.assertEquals
////
//
//    }
//
//

    @Test
    @Transactional
    public void shouldUpdateProductRatings(){
        // This test tests if the ratings for a product can be edited


        assertEquals(0, productCategoryRepo.findAll().size());
        assertEquals(0, productRepo.findAll().size());


        productCategoryRepo.save(productCategory1);
        productRepo.save(product1);
        // Product1 has rating of 4. Lets update to 5

        product1.setProductRating(5);
        productRepo.save(product1);


        Integer quantityExpected = 5;
       // Assert.assertEquals(quantityExpected, productRepo.getOne(product1.getProductId()).getProductRating());

//        Assert.assertEquals
//        Assert.assertEquals
//        Assert.assertEquals
//

    }


    @Test
    @Transactional
    public void shouldDeleteProduct(){


        assertEquals(0, productCategoryRepo.findAll().size());
        assertEquals(0, productRepo.findAll().size());


        productCategoryRepo.save(productCategory1);
        productCategoryRepo.save(productCategory2);

        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);

        productRepo.deleteById(product3.getProductId());

        assertEquals(2, productRepo.findAll().size());

        // Deleting one product from the Product category that has multiple products

        productSet.remove(product2);
        productRepo.deleteById(product2.getProductId());
        productCategoryRepo.save(productCategory1);

        assertEquals(1, productRepo.findAll().size());
        assertEquals(1, productCategoryRepo.getOne(productCategory1.getProductCategoryId()).getProducts().size());

//        Assert.assertEquals
//        Assert.assertEquals
//        Assert.assertEquals
//

    }

    @After
    public void tearDown() {
        productRepo.deleteAll();
        productCategoryRepo.deleteAll();
    }



}

