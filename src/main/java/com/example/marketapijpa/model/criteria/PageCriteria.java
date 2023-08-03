package com.example.marketapijpa.model.criteria;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.USE_DEFAULTS;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageCriteria {
    @JsonInclude(USE_DEFAULTS)
    private Integer page = 0;
    @JsonInclude(USE_DEFAULTS)
    private Integer count = 10;
}
