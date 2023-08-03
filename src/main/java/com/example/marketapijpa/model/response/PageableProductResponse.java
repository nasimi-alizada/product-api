package com.example.marketapijpa.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableProductResponse {
    private List<ProductResponse> products;
    private int lastPages;
    private long totalElements;
    private boolean hasNextPage;
}
