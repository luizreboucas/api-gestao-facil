package com.gestaofacil.api.domain.income;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record IncomeDTO(Long id, String description,Long client_id, BigDecimal value, LocalDateTime income_date, Long company_id) {

    public IncomeDTO(Income income){
        this(income.getId(), income.getDescription(), income.getClient().getId(), income.getValue(), income.getIncome_date(), income.getCompany().getId());
    }
}
