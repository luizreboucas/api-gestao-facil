package com.gestaofacil.api.controllers;

import com.gestaofacil.api.domain.client.ClientDTO;
import com.gestaofacil.api.domain.client.ClientUpdateDTO;
import com.gestaofacil.api.service.ClientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/{companyId}")
    public ResponseEntity<Page<ClientDTO>> getAllClients(@PathVariable Long companyId, Pageable pagination){
        var clients = clientService.getAllClients(pagination,companyId);
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO client, UriComponentsBuilder uriBuilder){
        var newClient = clientService.createClient(client);
        var uri = uriBuilder.path("/clients/{cliendId}").buildAndExpand(newClient.id()).toUri();
        return ResponseEntity.created(uri).body(newClient);
    }

    @PutMapping("/{clientId}")
    @Transactional
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long clientId,@RequestBody ClientUpdateDTO newClientData){
        var newClient = clientService.updateClient(newClientData, clientId);
        return ResponseEntity.ok(newClient);
    }

    @DeleteMapping("/{clientId}")
    @Transactional
    public ResponseEntity<String> deleteClient(@PathVariable Long clientId){
        var deleted = clientService.deleteClient(clientId);
        return ResponseEntity.ok("cliente deletado com sucesso");
    }

}
