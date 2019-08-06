package com.company.Marketplace.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "OrderHistory")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;


    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;


    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "OrderHistory_cartId")
    private Set<Product> products = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "OrderHistory_orderStatusId")
    private OrderStatus orderStatus = new OrderStatus();


    private Double cartTotal;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Double getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(Double cartTotal) {
        this.cartTotal = cartTotal;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

//    @Override
//    public boolean equals(Object object){
//
//        Cart cart = (Cart) object;
//
//
//        return (getProducts().containsAll(cart.getProducts())) &&
//                (this.getCartId() == cart.getCartId()) &&
//                (this.getOrderStatus().getOrderStatusName().equals(cart.getOrderStatus().getOrderStatusName())) ;
//
//
//
//    }
}
