package com.api.fakestoreapi.productservice.services;

import com.api.fakestoreapi.productservice.exceptions.ProductNotFoundException;
import com.api.fakestoreapi.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    List<String> getAllCategories();
    List<Product> getProductByCategory(String category);

    Product replaceProductById(Long id, Product product);
}
