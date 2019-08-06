package com.company.Marketplace.Controller;

import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.Service.ProductService;
import com.company.Marketplace.Service.ProductServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductServiceImplementation productService;

    @GetMapping
    public List<Product> getAllProductsFromService(){
        return productService.getAllProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody @Valid Product product){

        productService.addProductToDatabase(product);
        return product;
    }

    @GetMapping(value = "/productCategory/{productCategoryname}")
    public List<Product> getAllProductsFromProductCategory(@PathVariable String productCategoryname){
        return  productService.getProductsByProductCategory(productCategoryname);

    }

    @GetMapping(value = "/productCategory/aboveRating4/{productCategoryname}")
    public List<Product> getAllProductsFromProductCategoryAboveRating4(@PathVariable String productCategoryname){
        return  productService.getProductByProductCategoryAboveRating4(productCategoryname);

    }

    @GetMapping(value = "/sort/{sortOrder}")
    public List<Product> getAllProductsSortedOnPrice(@PathVariable String sortOrder){
        return  productService.sortProductsOnPrice(sortOrder);

    }



}
