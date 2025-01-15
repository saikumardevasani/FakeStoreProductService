package com.api.fakestoreapi.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

@Getter
@Setter
@Entity
public class Product extends BaseModel {

    private String title;
    private String description;
    private double price;
    private String image;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
    private int quantity;
}
