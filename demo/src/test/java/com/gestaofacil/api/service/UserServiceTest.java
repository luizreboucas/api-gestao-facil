package com.gestaofacil.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestaofacil.api.controllers.UserController;
import com.gestaofacil.api.domain.company.Company;
import com.gestaofacil.api.domain.company.CompanyCreationDTO;
import com.gestaofacil.api.domain.company.CompanyRepository;
import com.gestaofacil.api.domain.user.User;
import com.gestaofacil.api.domain.user.UserCreationDTO;
import com.gestaofacil.api.domain.user.UserDTO;
import com.gestaofacil.api.domain.user.UserRepository;
import com.gestaofacil.api.utils.RoleEnum;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import org.assertj.core.api.Assertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.net.URI;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("admin user should create a admin account/service")
    public void UC1cenario01() throws Exception {

        var companyData = new CompanyCreationDTO("company","1324345","12342342","CE","423","perto","rua adidas");
        Company company = new Company(companyData);
        company.setId(1L);
        var userData = new UserCreationDTO( RoleEnum.Admin,"luiz","luiz@email",company.getId(),"123","3123432","8532323");
        User user = new User(userData, company);
        when(this.userService.createUser(userData)).thenReturn(new UserDTO(user));
        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper()
                        .writeValueAsString(new ObjectMapper().writeValueAsString(userData))))
                .andExpect(status().isCreated());

    }

    private User createUser(UserCreationDTO userData, Company company){
        var user = new User(userData, company);
        return user;
    }

    private Company createCompany(CompanyCreationDTO companyData){
        var company = new Company(companyData);
        return company;
    }

}