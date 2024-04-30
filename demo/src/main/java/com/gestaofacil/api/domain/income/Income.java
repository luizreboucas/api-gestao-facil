package com.gestaofacil.api.domain.income;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gestaofacil.api.domain.client.Client;
import com.gestaofacil.api.domain.company.Company;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Income")
@Table(name = "incomes")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Income implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal value;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    private LocalDateTime income_date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public Income(IncomeDTO income, Company company){
        this.company = company;
        this.description = income.description();
        this.value = income.value();
        this.income_date = income.income_date();
    }

    public void update(IncomeDTO income){
        if(income.income_date() != null) this.income_date = income.income_date();
        if(income.description() != null) this.description = income.description();
        if(income.value() != null) this.value = income.value();
    }
}
