package com.ognjen.mini_e_commerce.specification;

import com.ognjen.mini_e_commerce.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public final class ProductSpecification {
    private ProductSpecification() {}

    public static Specification<Product> nameContains(String param) {
        return (root, cq, cb) -> (param == null || param.isBlank()) ? null : cb.like(cb.lower(root.get("name")), "%" + param.toLowerCase() + "%");
    }

    public static Specification<Product> categoryEquals(String cat) {
        return (root, cq, cb) -> (cat == null || cat.isBlank()) ? null : cb.equal(root.get("category"), cat);
    }

    public static Specification<Product> priceGte(BigDecimal min) {
        return (root, cq, cb) -> (min == null) ? null  : cb.greaterThanOrEqualTo(root.get("price"), min);
    }

    public static Specification<Product> priceLte(BigDecimal max) {
        return (root, cq, cb) -> (max == null) ? null : cb.lessThanOrEqualTo(root.get("price"), max);
    }

    public static Specification<Product> active(Boolean active) {
        return (root, cq, cb) -> (active == null)  ? null : cb.equal(root.get("active"), active);
    }
}