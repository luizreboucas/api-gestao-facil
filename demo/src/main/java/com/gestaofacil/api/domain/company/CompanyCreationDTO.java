package com.gestaofacil.api.domain.company;

public record CompanyCreationDTO(
        String name,
        String phone_number,
        String cnpj,
        String state,
        String number,
        String complement,
        String street) {
}
