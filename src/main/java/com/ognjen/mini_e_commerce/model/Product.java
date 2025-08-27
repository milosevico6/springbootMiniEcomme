package com.ognjen.mini_e_commerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(nullable = false, length = 255)
    private String name;

    @NotBlank @Column(nullable = false, length = 2000)
    private String description;

    @NotNull @Positive
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @NotBlank @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false)
    private boolean active = true;

}
