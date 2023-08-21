package com.devaleh.apisecurity.controllers;

import com.devaleh.apisecurity.dtos.ProductDto;
import com.devaleh.apisecurity.entities.Product;
import com.devaleh.apisecurity.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductDto productDto) {
        var product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productsList = service.findAll();
        if (!productsList.isEmpty()) {
            for (Product product : productsList) {
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }
        return ResponseEntity.ok().body(productsList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getOneProduct(@PathVariable UUID id) {
        var product = service.findById(id);
        product.add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Products List"));
        return ResponseEntity.ok().body(product);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id,
                                                 @RequestBody @Valid ProductDto productDto) {
        var product = service.findById(id);
        BeanUtils.copyProperties(productDto, product);
        return ResponseEntity.ok().body(service.save(product));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
