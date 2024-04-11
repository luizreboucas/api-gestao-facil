package com.gestaofacil.api.domain.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "Company")
@Table(name = "companys")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone_number;
    private String cnpj;
    private String state;
    private String number;
    private String complement;

    public Company(CompanyCreationDTO company){
        this.name = company.name();
        this.phone_number = company.phone_number();
        this.cnpj = company.cnpj();
        this.complement = company.complement();
        this.number = company.number();
        this.state = company.state();
    }

    public void update(CompanyCreationDTO company) {
        if(company.cnpj() != null) this.cnpj = company.cnpj();
        if(company.name() != null) this.name = company.name();
        if(company.phone_number() != null) this.phone_number = company.phone_number();
        if(company.complement() != null) this.complement = company.complement();
        if(company.number() != null) this.number = company.number();
        if(company.state() != null) this.state = company.state();
    }
}

