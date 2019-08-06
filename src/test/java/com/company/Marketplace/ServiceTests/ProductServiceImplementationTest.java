package com.company.Marketplace.ServiceTests;

import com.company.Marketplace.DAO.InventoryRepository;
import com.company.Marketplace.DAO.ProductCategoryRepository;
import com.company.Marketplace.DTO.Inventory;
import com.company.Marketplace.Service.ProductServiceImplementation;
import com.company.Marketplace.DAO.ProductRepository;
import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplementationTest {

    @Mock
    @Autowired
    ProductRepository productRepoMock;


    @InjectMocks
    ProductServiceImplementation productService;

    Product product1, product2, product3, product4;
    ProductCategory productCategory1, productCategory2;


    Set<Product> productSet1, productSet2;
    List<Product> productList;

    @Before
    public void setup() {


        productCategory1 = new ProductCategory();
        productCategory2 = new ProductCategory();

        productCategory1.setProductCategoryName("Shampoo");
        productCategory2.setProductCategoryName("Soap");

        product1 = new Product();
        product2 = new Product();
        product3 = new Product();
        product4 = new Product();

        product1.setProductCategory(productCategory1);
        product1.setProductName("Nivea");
        product1.setProductPrice(12.0);
        product1.setProductRating(5);
        product1.setQuantity(3);

        product2.setProductCategory(productCategory1);
        product2.setProductName("SheaMoisture");
        product2.setProductPrice(9.5);
        product2.setProductRating(3);
        product2.setQuantity(2);

        product3.setProductCategory(productCategory2);
        product3.setProductName("Dove");
        product3.setProductPrice(4.4);
        product3.setProductRating(5);
        product3.setQuantity(12);

        product4.setProductCategory(productCategory2);
        product4.setProductName("Pears");
        product4.setProductPrice(5.3);
        product4.setProductRating(5);
        product4.setQuantity(7);

        productSet1 = new HashSet<>();
        productSet1.add(product1);
        productSet1.add(product2);

        productSet2 = new HashSet<>();
        productSet2.add(product3);
        productSet2.add(product4);

        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
    }

    @Test
    public void shouldGetProductsByProductCategory() {


        List<Product> expectedList = Arrays.asList(product1, product2);

        productCategory1.setProducts(productSet1);
        productCategory2.setProducts(productSet2);

        when(productRepoMock.findAll()).thenReturn(productList);


        assertEquals(expectedList, productService.getProductsByProductCategory(
                product1.getProductCategory().getProductCategoryName()));


    }

    @Test
    public void shouldGetProductsByProductCategoryForRatingAbove4() {


        List<Product> expectedList = Arrays.asList(product3, product4);

        productCategory1.setProducts(productSet1);
        productCategory2.setProducts(productSet2);

        when(productRepoMock.findAll()).thenReturn(productList);


        assertEquals(expectedList, productService.getProductByProductCategoryAboveRating4(
                product3.getProductCategory().getProductCategoryName()));


    }

    @Test
    public void sortProductsOnPrice(){
        List<Product> expectedListAsc = Arrays.asList(product3, product4, product2, product1);
        List<Product> expectedListDesc = new ArrayList<>(expectedListAsc);
        Collections.reverse(expectedListDesc);

        when(productRepoMock.findAll()).thenReturn(productList);
        assertEquals(expectedListAsc, productService.sortProductsOnPrice("asc"));
        assertEquals(expectedListDesc, productService.sortProductsOnPrice("desc"));

    }


}
