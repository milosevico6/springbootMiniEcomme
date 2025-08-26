package com.ognjen.mini_e_commerce.service;

import com.ognjen.mini_e_commerce.model.Product;
import com.ognjen.mini_e_commerce.repo.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.ognjen.mini_e_commerce.specification.ProductSpecification.*;

@Service
public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }
    public Page<Product> list(String param, String category, BigDecimal minPrice, BigDecimal maxPrice,Boolean active, Pageable pageable) {
        Specification<Product> spec = Specification.where(nameContains(param))
                .and(categoryEquals(category))
                .and(priceGte(minPrice))
                .and(priceLte(maxPrice))
                .and(active(active));
        return repo.findAll(spec, pageable);
    }

    public Product get(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));

    }


}
