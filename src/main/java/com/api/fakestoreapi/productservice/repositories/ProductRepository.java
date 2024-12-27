package com.api.fakestoreapi.productservice.repositories;

import com.api.fakestoreapi.productservice.models.Product;
import com.api.fakestoreapi.productservice.projections.ProductWithTitleAndDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);

    Optional<Product> findByTitle(String title);
    List<Product> findByTitleContains(String str);
    List<Product>  findByTitleAndDescription(String title, String description);

    Optional<Product> findByImage(String url);


    Product getReferenceById(Long aLong);

    @Override
    void delete(Product entity);

    Product save(Product product);

    //HQL projections
    @Query("select p.title as title, p.description as description from Product p where p.id = :id")
    ProductWithTitleAndDescription someRandomQuery(@Param("id") Long id);

    //SQL
    @Query(value = "select p.title , p.description from product as p where p.id = :id", nativeQuery = true)
    ProductWithTitleAndDescription someOtherRandomQuery(@Param("id") Long id);
}
