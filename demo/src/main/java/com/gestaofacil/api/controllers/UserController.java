package com.gestaofacil.api.controllers;

import com.gestaofacil.api.domain.user.UserDTO;
import com.gestaofacil.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    public UserRepository repository;
    @GetMapping
    public ResponseEntity<Page<UserDTO>> getUsers(Pageable pagination){
        var users = repository.findAll(pagination).map(user -> new UserDTO(user));
        return ResponseEntity.ok(users);
    }
}
