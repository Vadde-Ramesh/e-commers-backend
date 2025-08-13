package com.ecom.service;

import java.util.List;

import com.ecom.entity.Product;

import jakarta.validation.Valid;

public interface ProductService {

	Product createProduct(@Valid Product product);

	List<Product> getAllProducts();

	Product getProductById(Long id);

	Product updateProduct(Long id, @Valid Product productDetails);

	void deleteProduct(Long id);

	List<Product> getProductsByCategory(String category);

	List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice);

	List<Product> searchProductsByName(String name);

	List<Product> getProductsSortedByNameAsc();

	List<Product> getProductsSortedByNameDesc();

}
