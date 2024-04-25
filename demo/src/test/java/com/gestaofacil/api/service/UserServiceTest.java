package com.gestaofacil.api.service;

import com.gestaofacil.api.domain.company.CompanyRepository;
import com.gestaofacil.api.domain.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private PasswordEncoder bcrypt;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CompanyRepository companyRepository;

    @Test
    @DisplayName("should save a user in the database")
    public void createUserScenario1() {

    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}