package com.api.fakestoreapi.productservice.services;

import com.api.fakestoreapi.productservice.exceptions.ProductNotFoundException;
import com.api.fakestoreapi.productservice.models.Category;
import com.api.fakestoreapi.productservice.models.Product;
import com.api.fakestoreapi.productservice.repositories.CategoryRepository;
import com.api.fakestoreapi.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
@Primary
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct =  productRepository.findById(id);

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException(id, "Product not found!");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public List<String> getAllCategories() {
        return List.of();
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return List.of();
    }

    @Override
    public Product replaceProductById(Long id, Product product) {
        return null;
    }

    @Override
    public Product updateProductById(Long id, Product product) {
        return null;
    }

    @Override
    public Product creteProduct(Product product) {
        // Before saving the product object save the category object first
        Category category= product.getCategory();
        if (category.getId() == null){
            // we need to save the category
           Category savedCategory = categoryRepository.save(category);
           product.setCategory(savedCategory);
        } else {
            // we should check whether the id is valid or not
        }
        Product savedProduct = productRepository.save(product);
        Optional<Category> optionalCategory = categoryRepository.findById(savedProduct.getCategory().getId());
        Category category1 = optionalCategory.get();
        savedProduct.setCategory(category1);

        return savedProduct;
    }

    @Override
    public void deleteProduct() {

    }
}
