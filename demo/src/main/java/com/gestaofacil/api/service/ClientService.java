package com.gestaofacil.api.service;

import com.gestaofacil.api.domain.client.Client;
import com.gestaofacil.api.domain.client.ClientDTO;
import com.gestaofacil.api.domain.client.ClientRepository;
import com.gestaofacil.api.domain.client.ClientUpdateDTO;
import com.gestaofacil.api.domain.company.CompanyRepository;
import com.gestaofacil.api.domain.income.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    public Page<ClientDTO> getAllClients(Pageable pagination, Long company_id) {
        if(!companyRepository.existsById(company_id)) throw new RuntimeException("id da empresa está incorreto");
        var clients = clientRepository.getAllClients(pagination, company_id);
        return clients.map(ClientDTO::new);
    }

    public ClientDTO createClient(ClientDTO client){
        var newClient = new Client(client);
        clientRepository.save(newClient);
        return new ClientDTO(newClient);
    }

    public ClientDTO updateClient(ClientUpdateDTO newData, Long clientId){
        if(!clientRepository.existsById(clientId)) throw new RuntimeException("cliente não encontrado");
        var client = clientRepository.findById(clientId).get();
        client.update(newData);
        return new ClientDTO(client);
    }

    public Boolean deleteClient(Long clientID){
        if(!clientRepository.existsById(clientID)) throw new RuntimeException("cliente não encontrado");
        clientRepository.deleteById(clientID);
        return true;
    }
}
