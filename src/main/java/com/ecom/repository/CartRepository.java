package com.ecom.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	Optional<Cart> findById(Long cartId);

	List<Cart> findByUserId(Long userId);

	boolean existsById(Long cartId);

	void deleteById(Long cartId);

	

}
