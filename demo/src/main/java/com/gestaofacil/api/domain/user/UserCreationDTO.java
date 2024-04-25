package com.gestaofacil.api.domain.user;

import com.gestaofacil.api.utils.RoleEnum;
import jakarta.validation.constraints.NotNull;

public record UserCreationDTO(
        RoleEnum role,
        @NotNull
        String email,
        @NotNull
        String name,
        Long company_id,
        @NotNull
        String password,
        @NotNull
        String cpf,
        String phone_number) {

}
