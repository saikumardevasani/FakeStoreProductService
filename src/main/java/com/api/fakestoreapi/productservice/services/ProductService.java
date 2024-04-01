package com.api.fakestoreapi.productservice.services;

import com.api.fakestoreapi.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
}
