package com.gestaofacil.api.domain.user;

import com.gestaofacil.api.utils.RoleEnum;

public record UserDTO(
        Long user_id,
        RoleEnum role,
        String name,
        String email,
        Long company_id,
        String password,
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
