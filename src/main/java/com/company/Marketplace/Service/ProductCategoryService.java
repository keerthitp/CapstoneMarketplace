package com.company.Marketplace.Service;

import com.company.Marketplace.DAO.ProductCategoryRepository;
import com.company.Marketplace.DTO.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepo;

    public List<ProductCategory> getAllProductCategory(){
        return productCategoryRepo.findAll();
    }

    public ProductCategory getProductCategoryById(Integer productCategoryId){
        return productCategoryRepo.getOne(productCategoryId);
    }
}
