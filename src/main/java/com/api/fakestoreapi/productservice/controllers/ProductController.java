package com.api.fakestoreapi.productservice.controllers;

import com.api.fakestoreapi.productservice.commons.AuthCommons;
import com.api.fakestoreapi.productservice.dtos.UserDto;
import com.api.fakestoreapi.productservice.exceptions.ProductNotFoundException;
import com.api.fakestoreapi.productservice.models.Product;
import com.api.fakestoreapi.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController  // This controller going to host rest Http API's
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private AuthCommons authCommons;

    //ToDo: Implement below code with response entity for reference watch ResponseEntity Lecture

    ProductController(@Qualifier("SelfProductService") ProductService productService, AuthCommons authCommons) {
        this.productService = productService;
        this.authCommons = authCommons;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id/*, @RequestHeader("auth") String token*/) throws ProductNotFoundException {

        ResponseEntity<Product> responseEntity;

        //call userservice validate token api to validate the token
//        UserDto userDto = authCommons.validateToken(token);

//        if (userDto == null){
//            responseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//            return responseEntity;
//        }

        Product product = productService.getProductById(id);
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;

//        if (product == null){
//            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            return responseEntity;
//        }


    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return productService.getAllCategories();
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category){
        return productService.getProductByCategory(category);
    }

    @PutMapping("{id}")
    public Product replaceProductById(@PathVariable("id") Long id,@RequestBody Product product){
        return productService.replaceProductById(id, product);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.creteProduct(product);
    }
}
