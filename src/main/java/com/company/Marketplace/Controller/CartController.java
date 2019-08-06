package com.company.Marketplace.Controller;

import com.company.Marketplace.DTO.Cart;
import com.company.Marketplace.DTO.Inventory;
import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
import com.company.Marketplace.Service.OrderHistoryService;
import com.company.Marketplace.Service.OrderHistoryServiceImplementation;
import com.company.Marketplace.Service.OrderStatusServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/order")
public class CartController {

    @Autowired
    OrderHistoryServiceImplementation orderHistoryService;


    @GetMapping
    public List<Cart> getAllOrdersFromOrderHistory(){

        return orderHistoryService.getAllOrdersFromOrderHistory();
    }

    @PostMapping
    public Cart addCartToOrderHistory(@RequestBody Cart cart){

        return orderHistoryService.addOrUpdateCartToOrderHistory(cart);
       // return cart;
    }

//    @GetMapping
//    public Cart addCartToOrderHistory(){
//
//        Cart cart =new Cart();
//
//        Product product1, product2, product3, product4;
//        ProductCategory productCategory1, productCategory2, productCategory3;
//
//        Set<Product> productSet1, productSet2;
//
//        Inventory inventory1, inventory2, inventory3, inventory4;
//
//        productCategory1 = new ProductCategory();
//        productCategory2 = new ProductCategory();
//        productCategory3 = new ProductCategory();
//
//        productCategory1.setProductCategoryName("Shoes");
//        productCategory1.setProductCategoryId(1);
//        productCategory2.setProductCategoryName("Shirts");
//        productCategory2.setProductCategoryId(2);
//        productCategory3.setProductCategoryName("Toys");
//        productCategory3.setProductCategoryId(3);
//
//
//        product1 = new Product();
//        product2 = new Product();
//        product3 = new Product();
//        product4 = new Product();
//
//        product1.setProductCategory(productCategory1);
//        product1.setProductName("Skechers");
//        product1.setProductPrice(90.0);
//        product1.setProductRating(4);
//        product1.setQuantity(5);
//
//        product2.setProductCategory(productCategory1);
//        product2.setProductName("Nike");
//        product2.setProductPrice(65.5);
//        product2.setProductRating(3);
//        product2.setQuantity(10);
//
//        product4.setProductCategory(productCategory1);
//        product4.setProductName("Reebok");
//        product4.setProductPrice(85.5);
//        product4.setProductRating(5);
//        product4.setQuantity(10);
//
//
//        product3.setProductCategory(productCategory2);
//        product3.setProductName("Express");
//        product3.setProductPrice(45.4);
//        product3.setProductRating(5);
//        product3.setQuantity(12);
//
//        productSet1 = new HashSet<>();
//        productSet1.add(product1);
//        productSet1.add(product2);
//        productSet1.add(product4);
//
//        productCategory1.setProducts(productSet1);
//
//        productSet2 = new HashSet<>();
//        productSet2.add(product3);
//
//        productCategory2.setProducts(productSet2);
//
//
//        cart.setCartId(10);
//        cart.setProducts(productSet1);
//        return cart;
//        //return  orderHistoryService.addOrUpdateCartToOrderHistory(cart);
//    }
}
