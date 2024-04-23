package com.gestaofacil.api.domain.client;

public record ClientDTO(Long id, String name, String cnpj, Long companyId) {

    public ClientDTO(Client client){
        this(client.getId(), client.getName(), client.getCnpj(), client.getCompany().getId());
    }
}
