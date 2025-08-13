package com.ecom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @Min(value = 0, message = "Price cannot be negative")
    private double price;

    private String description;
    
    @NotBlank(message = "Address must be present")
    private String address;
    
    @NotBlank(message = "category must be present")
    private String category;

	
}

