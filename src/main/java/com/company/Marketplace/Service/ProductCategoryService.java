package com.company.Marketplace.Service;

import com.company.Marketplace.DAO.ProductCategoryRepository;
import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProductCategoryService {


    ProductCategory getProductCategoryByProductCategoryName(String productCategoryName);

   // List<ProductCategory> getAllProductCategory();

    //List<Product> getAllProducts();
    ProductCategory getProductCategoryById(Integer productCategoryId);
    ProductCategory addProductCategoryToRepo(ProductCategory productCategory);
    String deleteProductCategoryFromDatabaseById(Integer id);
    List<ProductCategory> getAllProductCategories();
    //{
//        return productCategoryRepo.getOne(productCategoryId);
//    }
}
