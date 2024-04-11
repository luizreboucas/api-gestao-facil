package com.gestaofacil.api.domain.user;

import com.gestaofacil.api.domain.company.Company;
import com.gestaofacil.api.utils.RoleEnum;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

public record UserCreationDTO(
        RoleEnum role,
        @NotNull
        String name,
        @NotNull
        String email,
        Long company_id,
        @NotNull
        String password,
        @NotNull
        String cpf,
        String phone_number) {

}
