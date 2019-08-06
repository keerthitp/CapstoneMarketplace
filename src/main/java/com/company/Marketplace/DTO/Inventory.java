package com.company.Marketplace.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer inventoryId;

    @OneToOne
    @JoinColumn(name = "productId")
    private Product product;

    @NotNull
    private  Integer productQuantity; // quantity in inventory



    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public boolean equals(Object object){

        System.out.println("In equals=================");
        Inventory inventory = (Inventory) object;

        if(this == inventory ){
            return true;
        }
        boolean bool =(this.getInventoryId() == inventory.getInventoryId() ) &&
        (this.getProductQuantity() == inventory.getProductQuantity()) &&
                (this.getProduct().getProductId() == inventory.getProduct().getProductId());
        System.out.println("hhhhhhhhhhhhhhhhhhh"+ bool);
        System.out.println(this.getInventoryId()+"kkkk" + inventory.getInventoryId());
        System.out.println(this.getProductQuantity()+"----"+ inventory.getProductQuantity());
        System.out.println(this.getProduct().getProductId()+ "iiiiii"+ inventory.getProduct().getProductId());

        return (this.getInventoryId() == inventory.getInventoryId() ) &&
                (this.getProductQuantity() == inventory.getProductQuantity()) &&
                (this.getProduct().getProductId() == inventory.getProduct().getProductId());
    }
}
