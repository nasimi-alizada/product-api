package com.example.marketapijpa.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCriteria {
    private String name;
    private Integer priceFrom;
    private Integer priceTo;


}
