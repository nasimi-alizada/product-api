package com.example.marketapijpa.mapper;

import com.example.marketapijpa.dao.entity.ProductEntity;
import com.example.marketapijpa.model.request.PatchProductRequest;
import com.example.marketapijpa.model.request.ProductRequest;
import com.example.marketapijpa.model.request.UpdateProductRequest;
import com.example.marketapijpa.model.response.ProductResponse;

public class ProductMapper {
    public static ProductResponse buildProductResponse(ProductEntity product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static ProductEntity buildProductEntity(ProductRequest request) {
        return ProductEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }

    public static void buildUpdateProductEntity(ProductEntity productEntity,
                                                UpdateProductRequest updateProductRequest) {
        productEntity.setName(updateProductRequest.getName());
        productEntity.setDescription(updateProductRequest.getDescription());
        productEntity.setPrice(updateProductRequest.getPrice());

    }
    public  static void partialUpdateProductEntity(ProductEntity productEntity,
                                                   PatchProductRequest patchProductRequest){
        productEntity.setDescription(patchProductRequest.getDescription());
        productEntity.setPrice(patchProductRequest.getPrice());
    }
}
