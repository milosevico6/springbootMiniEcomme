package com.ognjen.mini_e_commerce.controller;


import com.ognjen.mini_e_commerce.model.Product;
import com.ognjen.mini_e_commerce.service.ProductService;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

@GetMapping
    public Page<Product> list(
            @RequestParam(required = false) String param,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Boolean active,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ){
        Sort s = Sort.by("id");
        Pageable pageable = PageRequest.of(page, size, s);
        return service.list(param, category, minPrice, maxPrice, active, pageable);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }




}
