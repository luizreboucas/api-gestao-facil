package com.gestaofacil.api.controllers;

import com.gestaofacil.api.domain.user.LoginDTO;
import com.gestaofacil.api.domain.user.User;
import com.gestaofacil.api.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;
    @PostMapping()
    public ResponseEntity<String> login(@RequestBody LoginDTO loginData){
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginData.email(), loginData.password());
        System.out.println(authenticationToken);
        var isAuthenticated = manager.authenticate(authenticationToken);
        System.out.println("e aqui");
        var token = tokenService.generateToken((User) isAuthenticated.getPrincipal());
        return ResponseEntity.ok(token);
    }
}
