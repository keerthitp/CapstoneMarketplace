package com.company.Marketplace.Service;

import com.company.Marketplace.DAO.ProductCategoryRepository;
import com.company.Marketplace.DAO.ProductRepository;
import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductServiceImplementation implements ProductService {

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductCategoryRepository productCategoryRepo;


//    @Override
//    public List<ProductCategory> getAllProductCategories() {
//        return productRepo.findAll()
//    }
    @Override
    public Product addProductToDatabase(Product product){
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }



    @Override
    public  List<Product> getProductsByProductCategory(String productCategoryName){

        List<Product> productList = productRepo.findAll();
        List<Product> productListOfProductCategory = new ArrayList<>();

        for(Product product : productList){
            if (product.getProductCategory().getProductCategoryName().toLowerCase()
                    .equals(productCategoryName.toLowerCase())){
                productListOfProductCategory.add(product);
            }
        }

        return productListOfProductCategory;
    }

    @Override
    public  List<Product> getProductByProductCategoryAboveRating4(String productCategoryName){
        List<Product> productList =
                productRepo.findAll().stream()
                .filter(product -> product.getProductRating() >= 4 &&
                        product.getProductCategory().getProductCategoryName().toLowerCase()
                                .equals(productCategoryName.toLowerCase())
                        ).collect(Collectors.toList());

        return productList;
    }

    @Override
    public List<Product> sortProductsOnPrice(String sort){

        List<String> sortingType = Arrays.asList("asc", "desc");
        if (! sortingType.contains(sort.toLowerCase()))
            throw new IllegalArgumentException("Expected sorting type: asc / desc");

        List<Product> productList = productRepo.findAll();
        List<Product> sortedList = new ArrayList<>();

        if (sort.toLowerCase().equals("asc"))
            sortedList = productList.stream()
                .sorted(Comparator.comparingDouble(Product::getProductPrice))
                    .collect(Collectors.toList());
        else
            sortedList = productList.stream()
                    .sorted(Comparator.comparingDouble(Product::getProductPrice ).reversed())
                    .collect(Collectors.toList());



        return sortedList;
    }

}
