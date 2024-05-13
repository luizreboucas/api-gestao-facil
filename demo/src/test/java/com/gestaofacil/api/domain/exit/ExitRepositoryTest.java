package com.gestaofacil.api.domain.exit;

import com.gestaofacil.api.domain.company.Company;
import com.gestaofacil.api.domain.company.CompanyCreationDTO;
import com.gestaofacil.api.domain.suplier.Suplier;
import com.gestaofacil.api.domain.suplier.SuplierDTO;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ExitRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("a user could register a new exit")
    public void UC2Cenario1(){
        CompanyCreationDTO companyData = new CompanyCreationDTO("luige","13123123",
                "173627463827", "CE", "500", "","rua chico");
        Company company = createCompany(companyData);
        SuplierDTO suplierData = new SuplierDTO(null,"fornecedor","312312312",company.getId());
        Suplier suplier = createSuplier(suplierData);
        System.out.println("suplier id do company => " + suplierData.company_id());
        ExitCreationDTO exitData = new ExitCreationDTO(new BigDecimal("500.4"),"leite em pó",
                ExitTypeEnum.CUSTO_VARIAVEL,LocalDateTime.now(),company.getId(),suplier.getId());
        Exit exit = createExit(exitData,company,suplier);
        Exit mockExit = new Exit(null,exit.getDescription(),exit.getValue(),exit.getType(),
                exit.getExit_date(),company,suplier);
        Assertions.assertThat(exit.getValue()).isEqualTo(mockExit.getValue());
        Assertions.assertThat(exit.getExit_date()).isEqualTo(mockExit.getExit_date());
        Assertions.assertThat(exit.getCompany().getId()).isEqualTo(mockExit.getCompany().getId());
        Assertions.assertThat(exit.getSuplier().getId()).isEqualTo(mockExit.getSuplier().getId());
    }

    @Test
    @DisplayName("a user could not register a new exit without company")
    public void UC2Cenario2(){
        CompanyCreationDTO companyData = new CompanyCreationDTO("luige","13123123",
                "173627463827", "CE", "500", "","rua chico");
        Company company = createCompany(companyData);
        SuplierDTO suplierData = new SuplierDTO(null,"fornecedor","312312312",company.getId());
        Suplier suplier = createSuplier(suplierData);
        System.out.println("suplier id do company => " + suplierData.company_id());
        ExitCreationDTO exitData = new ExitCreationDTO(new BigDecimal("500.4"),"leite em pó",
                ExitTypeEnum.CUSTO_VARIAVEL,LocalDateTime.now(),company.getId(),suplier.getId());

        Assertions.assertThatThrownBy(() -> createExit(exitData, null, suplier)).hasMessage("a saída deve ter uma empresa");
    }

    private Exit createExit(ExitCreationDTO exitData, Company company, Suplier suplier){
        Exit exit = new Exit(null,exitData.description(),exitData.value(),exitData.type(),
                exitData.exit_date(),company, suplier);
        if(company == null) throw new RuntimeException("a saída deve ter uma empresa");
        entityManager.persist(exit);
        entityManager.flush();
        return exit;
    }

    private Company createCompany(CompanyCreationDTO companyData){
        Company company = new Company(companyData);
        entityManager.persist(company);
        System.out.println("company => " + company.getId());
        return company;
    }
    private Suplier createSuplier(SuplierDTO suplierData){
        Suplier suplier = new Suplier(suplierData);
        Company company = entityManager.find(Company.class, suplierData.company_id());
        suplier.setCompany(company);
        entityManager.persist(suplier);
        entityManager.flush();
        return suplier;
    }
}