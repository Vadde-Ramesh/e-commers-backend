package com.ecom.service;

import com.ecom.entity.Product;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Create Product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Get All Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get Product by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    // Update Product
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setCategory(productDetails.getCategory());
        return productRepository.save(product);
    }

    // Delete Product
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    // Search by Category
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    // Search by Price Range
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    // Search by Name (like)
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // Sort by Product Name ASC
    public List<Product> getProductsSortedByNameAsc() {
        return productRepository.findAllByOrderByNameAsc();
    }

    // Sort by Product Name DESC
    public List<Product> getProductsSortedByNameDesc() {
        return productRepository.findAllByOrderByNameDesc();
    }
}
