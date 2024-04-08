package com.gestaofacil.api.controllers;

import com.gestaofacil.api.domain.user.User;
import com.gestaofacil.api.domain.user.UserDTO;
import com.gestaofacil.api.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    public UserRepository repository;
    @Autowired
    public PasswordEncoder bcrypt;
    @GetMapping
    public ResponseEntity<Page<UserDTO>> getUsers(Pageable pagination){
        var users = repository.findAll(pagination).map(user -> new UserDTO(user));
        return ResponseEntity.ok(users);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
        try{
            User newUser = new User(user);
            newUser.setPassword(bcrypt.encode(newUser.getPassword()));
            repository.save(newUser);
            return ResponseEntity.created(URI.create("/user" + newUser.getUser_id())).body(new UserDTO(newUser));
        }catch(RuntimeException exception) {
            throw new RuntimeException("erro ao criar usuário: ", exception);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new UserDTO(user.get()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserDTO user){
        Optional<User> oldUser = repository.findById(id);
        if(oldUser.isEmpty()) return ResponseEntity.notFound().build();
        oldUser.get().update(user);
        return ResponseEntity.ok(oldUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()) return ResponseEntity.notFound().build();
        repository.delete(user.get());
        return ResponseEntity.ok("usuário deletado com sucesso!");
    }
}
