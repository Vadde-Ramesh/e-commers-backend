package com.ecom.controller;

import com.ecom.entity.Product;
import com.ecom.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Create Product
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    // Get All Products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Get Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // Update Product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product productDetails) {
        return ResponseEntity.ok(productService.updateProduct(id, productDetails));
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    // Search by Category
    @GetMapping("/search/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    // Search by Price Range
    @GetMapping("/search/price")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        return ResponseEntity.ok(productService.getProductsByPriceRange(minPrice, maxPrice));
    }

    // Search by Product Name (like)
    @GetMapping("/search/name")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchProductsByName(name));
    }

    // Sort Products by Name Ascending
    @GetMapping("/sort/name/asc")
    public ResponseEntity<List<Product>> getProductsSortedByNameAsc() {
        return ResponseEntity.ok(productService.getProductsSortedByNameAsc());
    }

    // Sort Products by Name Descending
    @GetMapping("/sort/name/desc")
    public ResponseEntity<List<Product>> getProductsSortedByNameDesc() {
        return ResponseEntity.ok(productService.getProductsSortedByNameDesc());
    }
}
