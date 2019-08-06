package com.company.Marketplace.Service;

import com.company.Marketplace.DAO.ProductCategoryRepository;
import com.company.Marketplace.DAO.ProductRepository;
import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProductService {


     //List<ProductCategory> getAllProductCategories();
     List<Product> getProductsByProductCategory(String productCategoryName);
     List<Product> getProductByProductCategoryAboveRating4(String productCategoryName);
     List<Product> sortProductsOnPrice(String sort);
     List<Product> getAllProducts();
     Product addProductToDatabase(Product product);



}

