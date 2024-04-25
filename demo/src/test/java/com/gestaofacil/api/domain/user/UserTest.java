package com.gestaofacil.api.domain.user;

import com.gestaofacil.api.domain.company.Company;
import com.gestaofacil.api.domain.company.CompanyCreationDTO;
import com.gestaofacil.api.utils.RoleEnum;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestEntityManager
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserTest {
    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("should return a user from the database with all attributes")
    public void returnUser(){
        var companyData = new CompanyCreationDTO("luige", "12345","17382940955503", "CE","443","","rua pindes");
        var company = createCompany(companyData);
        var userData = new UserCreationDTO(RoleEnum.User,"luiz@gmail","luiz", company.getId(),"$2a$12$pxTliUhtenVI2tK8cMJcHe20lCIJ.6gmAHwM5054M7ZlpE/DB4d4m", "31424353446", "85496845069");
        var user = createUser(userData,company);

        Assertions.assertThat(entityManager.find(User.class, user.getUser_id())).isEqualTo(user);
    }

    @Test
    @DisplayName("should create a user with all args constructor")
    public void constructorUser(){
        var companyData = new CompanyCreationDTO("luige", "12345","17382940955503", "CE","443","","rua pindes");
        var company = createCompany(companyData);
        var user = new User(null,RoleEnum.User,"luiz", "luiz@re","$2a$12$pxTliUhtenVI2tK8cMJcHe20lCIJ.6gmAHwM5054M7ZlpE/DB4d4m","1234567","4343434",company);
        Assertions.assertThat(user.getUser_id()).isEqualTo(user.getUser_id());
        Assertions.assertThat(user.getRole()).isEqualTo(RoleEnum.User);
        Assertions.assertThat(user.getName()).isEqualTo("luiz");
        Assertions.assertThat(user.getEmail()).isEqualTo("luiz@re");
        Assertions.assertThat(user.getPassword()).isEqualTo("$2a$12$pxTliUhtenVI2tK8cMJcHe20lCIJ.6gmAHwM5054M7ZlpE/DB4d4m");
        Assertions.assertThat(user.getCpf()).isEqualTo("1234567");
        Assertions.assertThat(user.getPhone_number()).isEqualTo("4343434");
        Assertions.assertThat(user.getCompany()).isEqualTo(company);
    }

    @Test
    void update() {
        var companyData = new CompanyCreationDTO("luige", "12345","17382940955503", "CE","443","","rua pindes");
        var company = createCompany(companyData);
        var userData = new UserCreationDTO(RoleEnum.User,"luiz@gmail","luiz", company.getId(),"$2a$12$pxTliUhtenVI2tK8cMJcHe20lCIJ.6gmAHwM5054M7ZlpE/DB4d4m", "31424353446", "85496845069");
        var user = createUser(userData,company);
        var newUserData = new UserDTO(null,null,"luiza",null,null,null,null,null);
        user.update(newUserData);
        var userFromDatabase = entityManager.find(User.class,user.getUser_id());
        Assertions.assertThat(userFromDatabase.getUser_id()).isEqualTo(user.getUser_id());
        Assertions.assertThat(userFromDatabase.getName()).isEqualTo("luiza");
    }

    @Test
    @DisplayName("should get the credentials")
    public void getCredentialsOfTheUserCenario1(){
        var companyData = new CompanyCreationDTO("luige", "12345","17382940955503", "CE","443","","rua pindes");
        var company = createCompany(companyData);
        var userData = new UserCreationDTO(RoleEnum.User,"luiz@gmail","luiz", company.getId(),"$2a$12$pxTliUhtenVI2tK8cMJcHe20lCIJ.6gmAHwM5054M7ZlpE/DB4d4m", "31424353446", "85496845069");
        var user = createUser(userData,company);
        Assertions.assertThat(user.getUsername()).isEqualTo("luiz@gmail");
        Assertions.assertThat(user.isAccountNonExpired()).isTrue();
        Assertions.assertThat(user.isCredentialsNonExpired()).isTrue();
        Assertions.assertThat(user.isEnabled()).isTrue();
        Assertions.assertThat(user.isAccountNonLocked()).isTrue();
    }

    @Test
    @DisplayName("should all setters be working")
    public void createUserFromTheSetters(){
        var user = new User();

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