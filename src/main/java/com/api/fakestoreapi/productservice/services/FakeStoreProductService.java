package com.api.fakestoreapi.productservice.services;

import com.api.fakestoreapi.productservice.dtos.FakeStoreProductDto;
import com.api.fakestoreapi.productservice.exceptions.ProductNotFoundException;
import com.api.fakestoreapi.productservice.models.Category;
import com.api.fakestoreapi.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
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
    public Product getProductById(Long id) throws ProductNotFoundException {
       FakeStoreProductDto fakeStoreProductDto =
               restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

       if(fakeStoreProductDto == null){
           throw new ProductNotFoundException(id, "Product with id"+ id + " Not found!");
       }
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> response = new ArrayList<>();

        FakeStoreProductDto[] fakeStoreProductDtos =
                restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDto[].class);

        if(fakeStoreProductDtos == null){
            return null;
        }

        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
            response.add(convertFakeStoreDtoToProduct(fakeStoreProductDto));
        }
        return response;


    }

    @Override
    public List<String> getAllCategories() {

        List<String> categories = restTemplate.getForObject("https://fakestoreapi.com/products/categories", List.class);
        return categories;
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        List<Product> response = new ArrayList<>();

        FakeStoreProductDto[] fakeStoreProductDtos =
                restTemplate.getForObject("https://fakestoreapi.com/products/category/" + category, FakeStoreProductDto[].class);

        if(fakeStoreProductDtos == null){
            return null;
        }

        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
            response.add(convertFakeStoreDtoToProduct(fakeStoreProductDto));
        }
        return response;
    }

    @Override
    public Product replaceProductById(Long id, Product product) {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setImage(product.getImage());
        fakeStoreProductDto.setDescription(product.getDescription());


        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
       FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

       return convertFakeStoreDtoToProduct(response);
    }
}
