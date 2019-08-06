package com.company.Marketplace.Service;

import com.company.Marketplace.DAO.ProductCategoryRepository;
import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductCategoryServiceImplementation  implements  ProductCategoryService{

    @Autowired
    ProductCategoryRepository productCategoryRepo;

    @Override
    public  List<ProductCategory> getAllProductCategories(){

        return productCategoryRepo.findAll();
    }

    public String deleteProductCategoryFromDatabaseById(Integer id){


        boolean found = false;
        for (ProductCategory productCategory : productCategoryRepo.findAll()){

            if (productCategory.getProductCategoryId() == id)
                found = true;
        }

        if (found == false)
            throw new IllegalArgumentException("Invalid Product Category ID, please enter correct id");

        productCategoryRepo.deleteById(id);

        return "Product Category id: "+ id+ " deleted";
    }

    @Override
    public  ProductCategory addProductCategoryToRepo(ProductCategory productCategory){

        productCategoryRepo.save(productCategory);
        return productCategory;
    }
    @Override
    public ProductCategory getProductCategoryByProductCategoryName(String productCategoryName){
        return productCategoryRepo.findProductCategoryByProductCategoryName(productCategoryName);
    }

//    public List<ProductCategory> getAllProductCategory(){
//
//        return productCategoryRepo.findAll();
//    }

    public ProductCategory getProductCategoryById(Integer productCategoryId){

            boolean found = false;
            for (ProductCategory productCategory : productCategoryRepo.findAll()){

                if (productCategory.getProductCategoryId() == productCategoryId)
                    found = true;
            }
            if (found == false)
                throw new IllegalArgumentException("Invalid Product Category ID, please enter correct id");

//        if(productCategoryRepo.findAll().stream().filter(s-> s.getProductCategoryId() == productCategoryId) == null)
//            throw new IllegalArgumentException("Invalid Product Category ID");

        return productCategoryRepo.getOne(productCategoryId);
    }


}
