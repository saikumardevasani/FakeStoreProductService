package com.api.fakestoreapi.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductNotFoundExceptionDto {
    private String message;
}
