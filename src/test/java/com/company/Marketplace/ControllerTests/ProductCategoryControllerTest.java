package com.company.Marketplace.ControllerTests;
import com.company.Marketplace.Controller.ProductCategoryController;
import com.company.Marketplace.DTO.ProductCategory;
import com.company.Marketplace.Service.ProductCategoryServiceImplementation;
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
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ProductCategoryControllerTest {

    MockMvc mockMvc;

    @Mock
    ProductCategoryServiceImplementation productCategoryService;

    @InjectMocks
    ProductCategoryController productCategoryController;

    ProductCategory productCategory1 , productCategory2, productCategory3;

    @Before
    public void setup(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productCategoryController)

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



    }

    @Test
    public void shouldGetAllProductCategories() throws  Exception{

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory1);
        productCategoryList.add(productCategory2);

        when(productCategoryService.getAllProductCategories()).thenReturn(productCategoryList);

        mockMvc.perform(get("/productcategory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].productCategoryName", is(productCategoryList.get(0).getProductCategoryName())));
        verify(productCategoryService).getAllProductCategories();


    }

    @Test
    public void shouldGetProductCategoryById() throws  Exception{

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory1);
        productCategoryList.add(productCategory2);

        when(productCategoryService.getProductCategoryById(1)).thenReturn(productCategory1);

        mockMvc.perform(get("/productcategory/id/1"))
                .andExpect(status().isOk())
            //    .andExpect(jsonPath("$", hasSize(1))) // this needs to be done only for list
                .andExpect(jsonPath("$.productCategoryName", is(productCategory1.getProductCategoryName())));
        verify(productCategoryService).getProductCategoryById(1);


    }

    @Test
    public void shouldDeleteProductCategoryByIdInDatabase() throws  Exception{
//
//        List<ProductCategory> productCategoryList = new ArrayList<>();
//        productCategoryList.add(productCategory1);
//        productCategoryList.add(productCategory2);

        Integer id = 1; //
        when(productCategoryService.deleteProductCategoryFromDatabaseById(id))
                .thenReturn("Product Category id: "+ id+ " deleted");

        mockMvc.perform(MockMvcRequestBuilders.delete("/productcategory/id/"+ id))
                .andExpect(status().isOk());

        verify(productCategoryService).deleteProductCategoryFromDatabaseById(id);


    }

    @Test(expected = NestedServletException.class)
    public void shouldDeleteProductCategoryByIdNotInDatabase() throws  Exception{

       Integer id = 88; //
        when(productCategoryService.deleteProductCategoryFromDatabaseById(id))
                .thenThrow(IllegalArgumentException.class);

        mockMvc.perform(MockMvcRequestBuilders.delete("/productcategory/id/"+ id))
                .andExpect(status().is5xxServerError());

    }



}

