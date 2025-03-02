package com.api.fakestoreapi.productservice.controllers;

import com.api.fakestoreapi.productservice.exceptions.ProductNotFoundException;
import com.api.fakestoreapi.productservice.models.Product;
import com.api.fakestoreapi.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

//    @Test
//    void validGetProductByIdTest() throws ProductNotFoundException {
//
//        Product product = new Product();
//        product.setId(1L);
//        product.setTitle("MackBook Pro");
//        product.setDescription("MackBook Pro");
//        product.setPrice(150000.0);
//
////        when(productService.getProductById(1L))
////                .thenReturn(product);
////
////        Product actualProduct = productController.getProductById(1L).getBody();
////
////        assertEquals(product.getId(), actualProduct);
//    }
}