package com.gestaofacil.api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestaofacil.api.domain.company.Company;
import com.gestaofacil.api.domain.company.CompanyCreationDTO;
import com.gestaofacil.api.domain.user.LoginDTO;
import com.gestaofacil.api.domain.user.User;
import com.gestaofacil.api.domain.user.UserCreationDTO;
import com.gestaofacil.api.service.TokenService;
import com.gestaofacil.api.utils.RoleEnum;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mock;

    @Test
    @DisplayName("user created should login in the system")
    public void UC1cenario3() throws Exception {
        var companyData = new CompanyCreationDTO("company","1324345","12342342","CE","423","perto","rua adidas");
        Company company = new Company(companyData);
        var userData = new UserCreationDTO( RoleEnum.Admin,"luiz@gestao","luiz",company.getId(),"1234","3123432","8532323");
        User user = new User(userData, company);
        LoginDTO loginData = new LoginDTO(userData.email(), userData.password());
        mock.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginData)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("user should not login whith the wrong password")
    public void UC1cenario4() throws Exception {
        var loginData = new LoginDTO("luiz@gestao", "31231ddss");
        mock.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginData)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("user should not login whith the wrong email")
    public void UC1cenario5() throws Exception {
        var loginData = new LoginDTO("luiz@gestaoaaaaa", "1234");
        mock.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginData)))
                .andExpect(status().is4xxClientError());
    }

}