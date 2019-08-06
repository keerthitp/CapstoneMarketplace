package com.company.Marketplace.DAO;

import com.company.Marketplace.DTO.Product;
import com.company.Marketplace.DTO.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {



}
