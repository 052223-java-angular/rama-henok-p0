package com.revature.rhshop.services;

import java.util.List;
import com.revature.rhshop.daos.ProductDAO;
import com.revature.rhshop.models.Products;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductService {
    private final ProductDAO productDAO;

    public List<Products> getAll() {
        return productDAO.findAll();
    }

    public List<Products> searchProductByName(String name) {
        return productDAO.findByName(name);
    }

    public List<String> getAllCategories() {
        return productDAO.getAllCategories();
    }
   

    
}