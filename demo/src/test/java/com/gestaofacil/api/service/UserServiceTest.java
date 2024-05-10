package com.gestaofacil.api.service;

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
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import org.assertj.core.api.Assertions;
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserServiceTest {
    @Mock
    private PasswordEncoder bcrypt;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    EntityManager entityManager;
    @Test
    @DisplayName("admin user should create a admin account/service")
    public void UC1cenario01() {
        var companyData = new CompanyCreationDTO("company","1324345","12342342","CE","423","perto","rua adidas");
        Company company = new Company(companyData);
        entityManager.persist(company);
        var userData = new UserCreationDTO( RoleEnum.Admin,"luiz","luiz@email",company.getId(),"123","3123432","8532323");
        User user = new User(userData, company);
        entityManager.persist(user);
        var userFromDb = userRepository.findById(user.getUser_id()).get();
        Assertions.assertThat(user).isEqualTo(userFromDb);
    }

    private User createUser(UserCreationDTO userData, Company company){
        var user = new User(userData, company);
        entityManager.persist(user);
        return user;
    }

    private Company createCompany(CompanyCreationDTO companyData){
        var company = new Company(companyData);
        entityManager.persist(company);
        return company;
    }

}