package com.company.Marketplace.ControllerTests;

import com.company.Marketplace.Controller.ProductController;
import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
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

public class ProductControlerTest {

    MockMvc mockMvc;

    @Mock
    ProductServiceImplementation productService;

    @InjectMocks
    ProductController productController;


    Product product1, product2, product3, product4;
    ProductCategory productCategory1, productCategory2, productCategory3;

    Set<Product> productSet1, productSet2;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController)

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

    }


    @Test
    public void shouldGetAllProducts() throws  Exception{

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);

        when(productService.getAllProducts()).thenReturn(productList);

        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].productName", is(productList.get(0).getProductName())));
        verify(productService).getAllProducts();


    }

    @Test
    public void shouldGetAllProductsFromProductCategory() throws  Exception{

        String productCategoryname = product1.getProductCategory().getProductCategoryName();

        List<Product> productList1 = new ArrayList<>();
        productList1.add(product1);
        productList1.add(product2);
        productList1.add(product4);

        List<Product> productList2 = new ArrayList<>();
        productList2.add(product3);


        when(productService.getProductsByProductCategory(productCategoryname)).thenReturn(productList1);

        mockMvc.perform(get("/product/productCategory/" + productCategoryname))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].productName", is(productList1.get(0).getProductName())));
        verify(productService).getProductsByProductCategory(productCategoryname);


    }
    @Test
    public void shouldGetAllProductsFromProductCategoryAboveRating4() throws  Exception{

        String productCategoryname = product1.getProductCategory().getProductCategoryName();

        List<Product> productList1 = new ArrayList<>();
        productList1.add(product1);

        productList1.add(product4);



        when(productService.getProductByProductCategoryAboveRating4(productCategoryname)).thenReturn(productList1);

        mockMvc.perform(get("/product/productCategory/aboveRating4/" + productCategoryname))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]productCategory.productCategoryName",
                        is(productList1.get(0).getProductCategory().getProductCategoryName())));
        verify(productService).getProductByProductCategoryAboveRating4(productCategoryname);


    }

    @Test
    public void shouldGetAllProductsSortingOnPriceOrder() throws  Exception{

        String sortOrder = "asc";

        List<Product> productList1 = new ArrayList<>();
        productList1.add(product4);
        productList1.add(product2);
        productList1.add(product3);
        productList1.add(product1);


        when(productService.sortProductsOnPrice(sortOrder)).thenReturn(productList1);

        mockMvc.perform(get("/product/sort/" + sortOrder))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].productPrice",
                        is(productList1.get(0).getProductPrice())));
        verify(productService).sortProductsOnPrice(sortOrder);


    }
    @Test(expected = NestedServletException.class)
    public void shouldGetAllProductsSortingOnPriceOrderWhenOrderIsNotAscOrDesc() throws  Exception{

        String sortOrder = "ebcdic";

        List<Product> productList1 = new ArrayList<>();
        productList1.add(product4);
        productList1.add(product2);
        productList1.add(product3);
        productList1.add(product1);


        when(productService.sortProductsOnPrice(sortOrder)).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(get("/product/sort/" + sortOrder))
                .andExpect(status().is5xxServerError());
        verify(productService).sortProductsOnPrice(sortOrder);


    }

}