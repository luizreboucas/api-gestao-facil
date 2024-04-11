package com.gestaofacil.api.domain.company;

public record CompanyDTO (Long id, String name){

    public CompanyDTO(Company company){
        this(company.getId(), company.getName());
    }
}

