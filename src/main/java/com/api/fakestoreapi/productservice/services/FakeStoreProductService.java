package com.api.fakestoreapi.productservice.services;

import com.api.fakestoreapi.productservice.dtos.FakeStoreProductDto;
import com.api.fakestoreapi.productservice.models.Category;
import com.api.fakestoreapi.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{


    private RestTemplate restTemplate;
    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImage(dto.getImage());

        Category category = new Category();
        category.setDesc(dto.getCategory());
        product.setCategory(category);

        return product;
    }
    //call Fakestore APi to get the product with given id
    @Override
    public Product getProductById(Long id) {
       FakeStoreProductDto fakeStoreProductDto =
               restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

       if(fakeStoreProductDto == null){
           return null;
       }
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }
}
