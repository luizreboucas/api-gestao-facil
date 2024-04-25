package com.gestaofacil.api.domain.user;

import com.gestaofacil.api.domain.company.Company;
import com.gestaofacil.api.domain.company.CompanyRepository;
import com.gestaofacil.api.utils.RoleEnum;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        Long user_id,
        RoleEnum role,

        String name,

        String email,
        Company company,

        String password,

        String cpf,
        String phone_number) {
    public UserDTO(User user){
        this(user.getUser_id(),
                user.getRole(),
                user.getName(),
                user.getEmail(),
                user.getCompany(),
                user.getPassword(),
                user.getCpf(),
                user.getPhone_number());
    }
}
