package com.gestaofacil.api.controllers;

import com.gestaofacil.api.domain.user.User;
import com.gestaofacil.api.domain.user.UserCreationDTO;
import com.gestaofacil.api.domain.user.UserDTO;
import com.gestaofacil.api.domain.user.UserRepository;
import com.gestaofacil.api.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
@RequestMapping("/users")

public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<Page<UserDTO>> getUsers(Pageable pagination){
        var users = userService.getAllUsers(pagination);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserCreationDTO user){
        try{
            var newUser = userService.createUser(user);
            return ResponseEntity.created(URI.create("/user" + newUser.user_id())).body(newUser);
        }catch(RuntimeException exception) {
            throw new RuntimeException("erro ao criar usuário: ", exception);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
        var user = userService.getUser(id);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserDTO user){
        var updatedUser = userService.updateUser(id,user);
        if(updatedUser == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        var deleted = userService.deleteUser(id);
        if(!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("usuário deletado com sucesso!");
    }
}
