package com.devaleh.apisecurity.controllers;

import com.devaleh.apisecurity.dtos.UserModelDto;
import com.devaleh.apisecurity.entities.UserModel;
import com.devaleh.apisecurity.services.UserModelService;
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
@RequestMapping(value = "/users")
public class UserModelController {

    @Autowired
    private UserModelService service;

    @PostMapping
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserModelDto userModelDto) {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userModelDto, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userModel));
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> userList = service.findAll();
        if (!userList.isEmpty()) {
            for (UserModel user : userList) {
                UUID id = user.getUserId();
                user.add(linkTo(methodOn(UserModelController.class).getOneUser(id)).withSelfRel());
            }
        }
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserModel> getOneUser(@PathVariable UUID id) {
        var user = service.findById(id);
        user.add(linkTo(methodOn(UserModelController.class).getAllUsers()).withRel("Users List"));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable UUID id,
                                                 @RequestBody @Valid UserModelDto userModelDto) {
        var userModel = service.findById(id);
        BeanUtils.copyProperties(userModelDto, userModel);
        return ResponseEntity.ok().body(service.save(userModel));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
