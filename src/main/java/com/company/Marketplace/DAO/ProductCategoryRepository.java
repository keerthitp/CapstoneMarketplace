package com.company.Marketplace.DAO;

import com.company.Marketplace.DTO.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    ProductCategory findProductCategoryByProductCategoryName(String productCategoryName);
}
