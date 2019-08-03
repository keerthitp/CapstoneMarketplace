package com.company.Marketplace.Service;

import com.company.Marketplace.DAO.ProductCategoryRepository;
import com.company.Marketplace.DAO.ProductRepository;
import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService {

    @Autowired
    ProductCategoryRepository productCategoryRepo;

    @Autowired
    ProductRepository productRepo;


    public List<Product> getAllProductsFromProductCategory(){
        return null;
    }
}
