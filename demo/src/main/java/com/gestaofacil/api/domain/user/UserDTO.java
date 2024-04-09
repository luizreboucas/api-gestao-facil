package com.gestaofacil.api.domain.user;

import com.gestaofacil.api.utils.RoleEnum;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        Long user_id,
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
    public UserDTO(User user){
        this(user.getUser_id(),
                user.getRole(),
                user.getName(),
                user.getEmail(),
                user.getCompany_id(),
                user.getPassword(),
                user.getCpf(),
                user.getPhone_number());
    }
}
