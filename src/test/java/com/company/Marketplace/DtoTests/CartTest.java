package com.company.Marketplace.DtoTests;

import com.company.Marketplace.DAO.*;
import com.company.Marketplace.DTO.*;
import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTest {

    ProductCategory productCategory1 , productCategory2, productCategory3;
    Product product1, product2, product3;

    Inventory inventory;

    Set<Product> productSet;

    Customer customer;

    Cart cart1, cart2;

    @Autowired
    InventoryRepository inventoryRepo;

    @Autowired
    OrderHistoryRepository orderHistoryRepo;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductCategoryRepository productCategoryRepo;


    @Before
    public void setup(){




        productCategoryRepo.deleteAll();
        productRepo.deleteAll();
        customerRepo.deleteAll();
        orderHistoryRepo.deleteAll();

        inventory = new Inventory();

        cart1 = new Cart();
        cart2 = new Cart();

        customer = new Customer();
        customer.setCustomerName("Sandy");
        customer.setCustomerEmailId("Sandy@gmail.com");
        customer.setCustomerAddress("123 Main st, Plano, TX");

        productCategory1 = new ProductCategory();
        productCategory2 = new ProductCategory();
        productCategory3 = new ProductCategory();

        productCategory1.setProductCategoryName("Shoes");
        productCategory2.setProductCategoryName("Shirts");
        productCategory3.setProductCategoryName("Toys");


        product1 = new Product();
        product2 = new Product();
        product3 = new Product();

        product1.setProductCategory(productCategory1);
        product1.setProductName("Skechers");
        product1.setProductPrice(50.0);
        product1.setProductRating(4);
        product1.setQuantity(5);

        product2.setProductCategory(productCategory1);
        product2.setProductName("Nike");
        product2.setProductPrice(65.5);
        product2.setProductRating(3);
        product2.setQuantity(10);

        product3.setProductCategory(productCategory2);
        product3.setProductName("Express");
        product3.setProductPrice(45.4);
        product3.setProductRating(5);
        product3.setQuantity(12);

        productSet = new HashSet<>();

        productSet.add(product1);
        productSet.add(product2);

        productCategory1.setProducts(productSet);



    }

    @Test
    @Transactional
    public void shouldCreateCart(){

        customerRepo.save(customer);

        productCategoryRepo.save(productCategory1);
        productCategoryRepo.save(productCategory2);

        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);

        cart1.setCustomer(customer);

        Set<Product> productSet = new HashSet<>();
        productSet.add(product2);
        productSet.add(product3);

        cart1.setProducts(productSet);

        orderHistoryRepo.save(cart1);

        Assert.assertEquals(1, orderHistoryRepo.findAll().size());
        Assert.assertNotNull(orderHistoryRepo.getOne(cart1.getCartId()));
    }

    @Test
    @Transactional
    public void shouldUpdateCartWithQuantity(){
//        shouldCreateCart();

        customerRepo.save(customer);

        productCategoryRepo.save(productCategory1);
        productCategoryRepo.save(productCategory2);

        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);

        cart1.setCustomer(customer);

        Set<Product> productSet = new HashSet<>();
        productSet.add(product2);
        productSet.add(product3);

        cart1.setProducts(productSet);

        orderHistoryRepo.save(cart1);

        // added 2 products in cart - 2 & 3.
        Set<Product> cartProducts = orderHistoryRepo.getOne(cart1.getCartId()).getProducts();

        // Going to remove product 3(quantity 12) and add with the updated quantity (new quantity - 22)
        cartProducts.remove(product3);

        Product product = product3;

        Integer quantity = 22;
        product.setQuantity(quantity);

        // add this product to the set
        cartProducts.add(product);

        cart1.setProducts(cartProducts);
        orderHistoryRepo.save(cart1);

        int found =0;

        Iterator<Product> iterator = orderHistoryRepo.getOne(cart1.getCartId()).getProducts().iterator();

        Product tempProduct = null;
        while (iterator.hasNext()){
            tempProduct = iterator.next();

            if(tempProduct.equals(product3)){
                break;

            }


        }

        Assert.assertEquals(quantity, tempProduct.getQuantity());


    }


    @Test
    @Transactional
    public void shouldUpdateCartWithNewProduct(){
//        shouldCreateCart();

        customerRepo.save(customer);

        productCategoryRepo.save(productCategory1);
        productCategoryRepo.save(productCategory2);

        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);

        cart1.setCustomer(customer);

        Set<Product> productSet = new HashSet<>();
        productSet.add(product2);
        productSet.add(product3);

        cart1.setProducts(productSet);

        orderHistoryRepo.save(cart1);


        Product product4 = new Product();
        product4.setProductCategory(productCategory3);
        product4.setProductRating(3);
        product4.setProductPrice(65.0);
        product4.setProductName("Scooter");

        inventory.setProduct(product4);
        inventory.setProductQuantity(101);

        inventoryRepo.save(inventory);

        product4.setQuantity(5);
        productRepo.save(product4);

        // added 2 products in cart - 2 & 3.
        Set<Product> cartProducts = orderHistoryRepo.getOne(cart1.getCartId()).getProducts();

        // Going to remove product 3(quantity 12) and add with the updated quantity (new quantity - 22)
        cartProducts.add(product4);
        cart1.setProducts(cartProducts);

        orderHistoryRepo.save(cart1);

        int found =0;

        Assert.assertTrue(orderHistoryRepo.getOne(cart1.getCartId()).getProducts().contains(product4));





    }

}
