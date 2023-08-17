package com.devaleh.apisecurity.controllers;

import com.devaleh.apisecurity.dtos.ProductDto;
import com.devaleh.apisecurity.entities.Product;
import com.devaleh.apisecurity.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductDto productDto) {
        var product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return ResponseEntity.ok().body(service.insert(product));
    }
}
