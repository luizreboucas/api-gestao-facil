package com.gestaofacil.api.domain.exit;

import com.gestaofacil.api.domain.company.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExitDTO(Long id,String description, @NotNull BigDecimal value, @NotNull ExitTypeEnum type, @NotNull LocalDateTime exit_date, @NotNull Long company_id) {

    public ExitDTO(Exit exit){
        this(exit.getId(),exit.getDescription(), exit.getValue(), exit.getType(), exit.getExit_date(), exit.getCompany().getId());
    }
}
