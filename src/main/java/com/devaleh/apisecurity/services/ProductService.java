package com.devaleh.apisecurity.services;

import com.devaleh.apisecurity.entities.Product;
import com.devaleh.apisecurity.repositories.ProductRepository;
import com.devaleh.apisecurity.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public Product save(Product product) {
        return repository.save(product);
    }

    public List<Product> findAll() {
        Sort sort = Sort.by("price").ascending();
        return repository.findAll(sort);
    }

    public Product findById(UUID id) {
        Optional<Product> product = repository.findById(id);
        return product.orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
