package com.company.Marketplace.Controller;

import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
import com.company.Marketplace.Service.ProductCategoryServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/productcategory")
public class ProductCategoryController {

    @Autowired
    ProductCategoryServiceImplementation productCategoryService;

    @PostMapping
    public ProductCategory addProductCategory(@RequestBody ProductCategory productCategory) {

        return productCategoryService.addProductCategoryToRepo(productCategory);

    }

    @GetMapping
    public List<ProductCategory> getProductCategories() {
        return productCategoryService.getAllProductCategories();

    }

    @GetMapping(value = "/id/{id}")
    public ProductCategory getProductCategoryFromService(@PathVariable Integer id) {

        return productCategoryService.getProductCategoryById(id);
    }

    @DeleteMapping(value = "/id/{id}")
    public String deleteProductCategoryById(@PathVariable Integer id) {
        return productCategoryService.deleteProductCategoryFromDatabaseById(id);
    }

}
