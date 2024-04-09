package com.gestaofacil.api.service;

import com.gestaofacil.api.domain.user.User;
import com.gestaofacil.api.domain.user.UserDTO;
import com.gestaofacil.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UserRepository userRepository;
    public UserDTO createUser(UserDTO user){
        var newUser = new User(user);
        newUser.setPassword(bcrypt.encode(user.password()));
        userRepository.save(newUser);
        return new UserDTO(newUser);
    }

    public Page<UserDTO> getAllUsers(Pageable pagination){
        return userRepository.findAll(pagination).map(UserDTO::new);
    }
    public UserDTO getUser(Long id){
        var user = userRepository.findById(id);
        if(user.isEmpty()) return null;
        return new UserDTO(user.get());
    }

    public UserDTO updateUser(Long id, UserDTO newUserData){
        var user = userRepository.findById(id);
        if(user.isEmpty()) return  null;
        user.get().update(newUserData);
        return new UserDTO(user.get());
    }

    public boolean deleteUser(Long id){
        var user = userRepository.findById(id);
        if(user.isEmpty()) return false;
        userRepository.delete(user.get());
        return true;
    }
}