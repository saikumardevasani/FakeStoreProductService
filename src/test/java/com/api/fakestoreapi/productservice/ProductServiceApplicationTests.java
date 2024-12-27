package com.api.fakestoreapi.productservice;

import com.api.fakestoreapi.productservice.models.Category;
import com.api.fakestoreapi.productservice.models.Product;
import com.api.fakestoreapi.productservice.projections.ProductWithTitleAndDescription;
import com.api.fakestoreapi.productservice.repositories.CategoryRepository;
import com.api.fakestoreapi.productservice.repositories.ProductRepository;
import com.api.fakestoreapi.productservice.services.ProductService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;
	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	public void testTC(){
		Optional<Category> optionalCategory = categoryRepository.findById(2L);
		Category category = optionalCategory.get();
		System.out.println("Fetching related to above categories");
		List<Product> products = category.getProducts();
		//System.out.println(products.get(0).getTitle());

		ProductWithTitleAndDescription product = productRepository.someRandomQuery(2L);
		System.out.println(product.getTitle());
		System.out.println(product.getDescription());

		ProductWithTitleAndDescription productSQL = productRepository.someOtherRandomQuery(2L);
		System.out.println(product.getTitle());
		System.out.println(product.getDescription());
		System.out.println("DEBUG");
	}
}
