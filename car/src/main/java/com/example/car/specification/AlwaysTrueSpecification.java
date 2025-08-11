package com.example.car.specification;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


public class AlwaysTrueSpecification<T> implements Specification<T> {

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        // This creates a predicate that always evaluates to true, effectively applying no filter.
        return criteriaBuilder.and();
    }

    // You can also create a static factory method for convenience
    public static <T> Specification<T> alwaysTrue() {
        return (root, query, cb) -> cb.and();
    }
}
