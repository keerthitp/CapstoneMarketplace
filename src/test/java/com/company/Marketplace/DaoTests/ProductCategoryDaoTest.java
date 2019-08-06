package com.company.Marketplace.DaoTests;


import com.company.Marketplace.DAO.ProductCategoryRepository;
import com.company.Marketplace.DAO.ProductRepository;
import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    ProductCategory productCategory1 , productCategory2, productCategory3;
    Product product1, product2, product3;

    Set<Product> productSet;

    @Autowired
    ProductCategoryRepository productCategoryRepo;

    @Autowired
    ProductRepository productRepo;

    @Before
    public void setup(){

        productCategoryRepo.deleteAll();
        productRepo.deleteAll();

        productCategory1 = new ProductCategory();
        productCategory2 = new ProductCategory();
        productCategory3 = new ProductCategory();

        productCategory1.setProductCategoryName("Shoes");
        productCategory2.setProductCategoryName("Shirts");
        productCategory3.setProductCategoryName("Toys");


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
    public void shouldAddProductCategories(){

        productCategoryRepo.deleteAll();
        Assert.assertEquals(0, productCategoryRepo.findAll().size());

        productCategoryRepo.save(productCategory1);
        productCategoryRepo.save(productCategory2);
        productCategoryRepo.save(productCategory3);

        Assert.assertEquals(3, productCategoryRepo.findAll().size());
        Assert.assertNotNull(productCategory3.getProductCategoryId());

    }

    @Test
    @Transactional
    public  void shouldDeleteProductCategories(){




        productCategoryRepo.save(productCategory1);
        productCategoryRepo.save(productCategory2);
        productCategoryRepo.save(productCategory3);

        productCategoryRepo.deleteById(productCategory3.getProductCategoryId());

        Assert.assertEquals(2, productCategoryRepo.findAll().size());

        Optional<ProductCategory> productCategoryTest = productCategoryRepo.findById(
                productCategory3.getProductCategoryId());

        Assert.assertFalse(productCategoryTest.isPresent());


    }


    @Test
    @Transactional
    public  void shouldGetProductCategoryByName(){




        productCategoryRepo.save(productCategory1);
        productCategoryRepo.save(productCategory2);
        productCategoryRepo.save(productCategory3);

        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);

        Assert.assertEquals("Toys",
                productCategoryRepo.findProductCategoryByProductCategoryName("Toys").getProductCategoryName());


        Assert.assertNull(
                productCategoryRepo.findProductCategoryByProductCategoryName("Cookware"));
    }

    @Test
    @Transactional
    public  void shouldGetProductsByProductCategory(){

        productCategoryRepo.save(productCategory1);
        productCategoryRepo.save(productCategory2);
        productCategoryRepo.save(productCategory3);

        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);

        Set<Product> expectedSet;

        expectedSet = productCategoryRepo
                .getOne(productCategory1.getProductCategoryId())
                .getProducts();


        Assert.assertEquals(expectedSet, productSet);

    }



}
