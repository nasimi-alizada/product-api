package com.example.marketapijpa.service;

import com.example.marketapijpa.dao.entity.ProductEntity;
import com.example.marketapijpa.dao.repository.ProductRepository;
import com.example.marketapijpa.mapper.ProductMapper;
import com.example.marketapijpa.model.criteria.PageCriteria;
import com.example.marketapijpa.model.criteria.ProductCriteria;
import com.example.marketapijpa.model.request.PatchProductRequest;
import com.example.marketapijpa.model.request.ProductRequest;
import com.example.marketapijpa.model.response.PageableProductResponse;
import com.example.marketapijpa.model.response.ProductResponse;
import com.example.marketapijpa.model.request.UpdateProductRequest;
import com.example.marketapijpa.service.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.marketapijpa.mapper.ProductMapper.*;
import static com.example.marketapijpa.model.enums.ProductStatus.ACTIVE;
import static com.example.marketapijpa.model.enums.ProductStatus.OUT_OF_STOCK;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public ProductResponse getProductById(Long id) {

        var product = fetchProductIfExist(id);
        if (product.getStatus() != ACTIVE) {
            return null;
        }
        return buildProductResponse(product);
    }

    public PageableProductResponse getProducts(PageCriteria pageCriteria,
                                               ProductCriteria productCriteria) {
        var productPages = productRepository.findAll(new ProductSpecification(productCriteria),
                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount()));

        return mapToPageableResponse(productPages);
    }


    public void saveProduct(ProductRequest productRequest) {
        productRepository.save(buildProductEntity(productRequest));
    }

    public void updateProduct(Long id, UpdateProductRequest updateProductRequest) {
        var productEntity = fetchProductIfExist(id);
        buildUpdateProductEntity(productEntity, updateProductRequest);
        productRepository.save(productEntity);
    }

    public void partialUpdateProduct(Long id,
                                     PatchProductRequest patchProductRequest) {
        var productEntity = fetchProductIfExist(id);
        partialUpdateProductEntity(productEntity, patchProductRequest);
        productRepository.save(productEntity);
    }

    public void deleteProduct(Long id) {
        var product = fetchProductIfExist(id);
        product.setStatus(OUT_OF_STOCK);
        productRepository.save(product);
    }

    private ProductEntity fetchProductIfExist(Long id) {
        return productRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("PRODUCT_NOT_FOUND")
                );


    }

    private PageableProductResponse mapToPageableResponse(Page<ProductEntity> productEntityPage) {
        return PageableProductResponse.builder()
                .products(productEntityPage.getContent().stream().map(ProductMapper::buildProductResponse).collect(Collectors.toList()))
                .totalElements(productEntityPage.getTotalPages())
                .lastPages(productEntityPage.getTotalPages())
                .hasNextPage(productEntityPage.hasNext())
                .build();

    }

}
