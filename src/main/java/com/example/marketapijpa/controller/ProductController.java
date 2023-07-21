package com.example.marketapijpa.controller;

import com.example.marketapijpa.model.request.PatchProductRequest;
import com.example.marketapijpa.model.request.ProductRequest;
import com.example.marketapijpa.model.request.UpdateProductRequest;
import com.example.marketapijpa.model.response.ProductResponse;
import com.example.marketapijpa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<ProductResponse> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveProduct(@RequestBody ProductRequest productRequest) {
        productService.saveProduct(productRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateProduct(@PathVariable Long id,
                              @RequestBody UpdateProductRequest request) {

        productService.updateProduct(id, request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void partialUpdateProduct(@PathVariable Long id,
                                     @RequestBody PatchProductRequest patchProductRequest) {
        productService.partialUpdateProduct(id, patchProductRequest);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

    }


}
