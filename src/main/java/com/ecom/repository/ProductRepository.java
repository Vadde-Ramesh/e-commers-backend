package com.ecom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Optional<Product> findById(Long id);

	List<Product> findByCategoryIgnoreCase(String category);

	List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

	List<Product> findByNameContainingIgnoreCase(String name);

	List<Product> findAllByOrderByNameAsc();

	List<Product> findAllByOrderByNameDesc();

}
