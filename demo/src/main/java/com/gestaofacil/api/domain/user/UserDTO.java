package com.gestaofacil.api.domain.user;

import com.gestaofacil.api.utils.RoleEnum;

public record UserDTO(Long user_id,RoleEnum role, String name, String email, Long company_id) {
    public UserDTO(User user){
        this(user.getUser_id(), user.getRole(), user.getName(), user.getEmail(), user.getCompany_id());
    }
}
