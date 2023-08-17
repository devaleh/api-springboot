package com.devaleh.apisecurity.services;

import com.devaleh.apisecurity.entities.Product;
import com.devaleh.apisecurity.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public Product insert(Product product) {
        return repository.save(product);
    }
}
