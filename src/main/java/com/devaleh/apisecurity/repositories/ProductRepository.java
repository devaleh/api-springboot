package com.devaleh.apisecurity.repositories;

import com.devaleh.apisecurity.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}