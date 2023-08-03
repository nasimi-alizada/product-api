package com.example.marketapijpa.service.specification;

import com.example.marketapijpa.dao.entity.ProductEntity;
import com.example.marketapijpa.model.criteria.ProductCriteria;
import com.example.marketapijpa.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.example.marketapijpa.constant.CriteriaConstant.NAME;
import static com.example.marketapijpa.util.PredicateUtil.applyLikePattern;
import static org.springframework.http.HttpHeaders.AGE;

@AllArgsConstructor
public class ProductSpecification implements Specification<ProductEntity> {
    private ProductCriteria productCriteria;

    @Override
    public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        var predicates = PredicateUtil.builder()
                .addNullSafety(productCriteria.getName(),
                        name -> cb.like(root.get(NAME), applyLikePattern(name)))

                .addNullSafety(productCriteria.getPriceFrom(),
                        ageFrom -> cb.greaterThanOrEqualTo(root.get(AGE), ageFrom))
                .addNullSafety(productCriteria.getPriceTo(),
                        ageTo -> cb.lessThanOrEqualTo(root.get(AGE), ageTo))
                .build();
        return cb.and(predicates);
    }
}
