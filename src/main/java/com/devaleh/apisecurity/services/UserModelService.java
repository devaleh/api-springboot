package com.devaleh.apisecurity.services;

import com.devaleh.apisecurity.entities.UserModel;
import com.devaleh.apisecurity.repositories.UserRepository;
import com.devaleh.apisecurity.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserModelService {

    @Autowired
    private UserRepository repository;

    public UserModel save(UserModel user) {
        return repository.save(user);
    }

    public List<UserModel> findAll() {
        return repository.findAll();
    }

    public UserModel findById(UUID id) {
        Optional<UserModel> user = repository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
