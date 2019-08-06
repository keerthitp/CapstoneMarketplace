package com.company.Marketplace.ServiceTests;

import com.company.Marketplace.DAO.InventoryRepository;
import com.company.Marketplace.DAO.OrderHistoryRepository;
import com.company.Marketplace.DTO.*;
import com.company.Marketplace.Service.OrderHistoryServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class OrderHistoryServiceImplementationTest {

    @Mock
    @Autowired
    OrderHistoryRepository orderHistoryRepoMock;


    @Mock
    @Autowired
    InventoryRepository inventoryRepoMock;

    @InjectMocks
    OrderHistoryServiceImplementation orderHistoryService;

    Cart cart;
    Customer customer;
    Product product1, product2, product3;
    ProductCategory productCategory1, productCategory2;
    Inventory inventory1, inventory2, inventory3;
    Set<Product> productSet;
    OrderStatus orderStatus;

    @Before
    public void setup(){

        orderStatus = new OrderStatus();

        customer = new Customer();
        customer.setCustomerAddress("123 main st, plano, TX");
        customer.setCustomerName("Sandy");
        customer.setCustomerId(12);

        productCategory1 = new ProductCategory();
        productCategory2 = new ProductCategory();

        productCategory1.setProductCategoryId(1);
        productCategory1.setProductCategoryName("Chocolates");

        productCategory2.setProductCategoryId(2);
        productCategory2.setProductCategoryName("Cookies");

        product1 = new Product();
        product1.setProductId(1);
        product1.setProductName("Lindt");
        product1.setProductCategory(productCategory1);
        product1.setQuantity(5);

        product2 = new Product();
        product2.setProductId(2);
        product2.setProductName("Godiva");
        product2.setProductCategory(productCategory1);
        product2.setQuantity(100);

        product3 = new Product();
        product3.setProductId(3);
        product3.setProductName("Grandmas");
        product3.setProductCategory(productCategory2);
        product3.setQuantity(1);

        productSet = new HashSet<>();
        productSet.add(product1);
        productSet.add(product2);


        inventory1 = new Inventory();
        inventory1.setInventoryId(1);
        inventory1.setProductQuantity(10);
        inventory1.setProduct(product1);

        inventory2 = new Inventory();
        inventory2.setInventoryId(2);
        inventory2.setProductQuantity(10);
        inventory2.setProduct(product2);

        inventory3 = new Inventory();
        inventory3.setInventoryId(3);
        inventory3.setProductQuantity(0);
        inventory3.setProduct(product3);



        cart = new Cart();

    }

    @Test(expected = IllegalArgumentException.class)
    public void newOrderWithProductAskingGreaterThanInventory(){

        Cart cartExpected = new Cart();
        cartExpected.setCartId(1);
        cartExpected.setProducts(productSet);
        cartExpected.setOrderStatus(orderStatus);



        cart.setProducts(productSet);
        //cart.setCartId(1);

        when(inventoryRepoMock.findByProduct(product1.getProductId())).thenReturn(inventory1);
        when(inventoryRepoMock.findByProduct(product2.getProductId())).thenReturn(inventory2);
//        when(inventoryRepoMock.findByProduct(product3.getProductId())).thenReturn(inventory3);
  //      when(orderHistoryRepoMock.save(cart)).thenReturn(cart);
        assertTrue(orderHistoryService.addOrUpdateCartToOrderHistory(cart).getCartId()!=null);
        //assertEquals(cartExpected, );

    }





}
