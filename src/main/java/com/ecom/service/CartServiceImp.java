package com.ecom.service;

import com.ecom.entity.Cart;
import com.ecom.entity.Product;
import com.ecom.entity.Users;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.repository.CartRepository;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {

	@Autowired
    private final CartRepository cartRepository;
	@Autowired
    private final UsersRepository usersRepository;
	@Autowired
    private final ProductRepository productRepository;

    public Cart addCart(Integer userId, List<Integer> productIds) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Product> products = productRepository.findAllById(productIds);
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products found for given IDs");
        }

        double totalPrice = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProducts(products);
        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + cartId));
    }

    public List<Cart> getUserCarts(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public void deleteCart(Long cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new ResourceNotFoundException("Cart not found with id: " + cartId);
        }
        cartRepository.deleteById(cartId);
    }
}
