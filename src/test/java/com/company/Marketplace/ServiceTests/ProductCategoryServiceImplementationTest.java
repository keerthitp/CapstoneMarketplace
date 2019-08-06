package com.company.Marketplace.ServiceTests;

import com.company.Marketplace.DAO.ProductCategoryRepository;
import com.company.Marketplace.DTO.ProductCategory;
import com.company.Marketplace.Service.ProductCategoryServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductCategoryServiceImplementationTest {

    @Mock
    @Autowired
    ProductCategoryRepository productCategoryRepoMock;

    @InjectMocks
    ProductCategoryServiceImplementation  productCategoryService;

    ProductCategory productCategory1 , productCategory2, productCategory3;
    List<ProductCategory> productCategoryList;

    @Before
    public void setup() {

        productCategoryList = new ArrayList<>();

        productCategory1 = new ProductCategory();
        productCategory2 = new ProductCategory();
        productCategory3 = new ProductCategory();

        productCategory1.setProductCategoryName("Shoes");
        productCategory2.setProductCategoryName("Shirts");
        productCategory3.setProductCategoryName("Toys");

        productCategoryList = Arrays.asList(productCategory1,
                                            productCategory2,
                                            productCategory3
                                            );

    }

    @Test
    public void shouldGetAllProductCategories(){
        List<ProductCategory> expectedList = Arrays.asList(productCategory1,
                                                            productCategory2,
                                                            productCategory3
                                                            );


        when(productCategoryRepoMock.findAll()).thenReturn(productCategoryList);
        assertEquals(expectedList, productCategoryService.getAllProductCategories());
    }

    @Test
    public  void shouldGetProductCategoryByProductCategoryName(){
        ProductCategory expectedProductCategory = productCategory3;

        when(productCategoryRepoMock.
                findProductCategoryByProductCategoryName(productCategory3.getProductCategoryName()))
                    .thenReturn(productCategory3);

        assertEquals(expectedProductCategory,
                productCategoryService.getProductCategoryByProductCategoryName(
                        productCategory3.getProductCategoryName()
                ));
    }
    }
