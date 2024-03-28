package com.gestaofacil.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public ResponseEntity returnHello(){
        var hello = new HelloDTO("Aplicação funcionando corretamente");
        return ResponseEntity.ok(hello);
    }

}
