package com.ecom.controller;

import com.ecom.entity.Cart;
import com.ecom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> addCart(
            @RequestParam Integer userId,
            @RequestBody List<Integer> productIds) {
        return ResponseEntity.ok(cartService.addCart(userId, productIds));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.getCart(cartId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cart>> getUserCarts(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getUserCarts(userId));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok("Cart deleted successfully");
    }
}
