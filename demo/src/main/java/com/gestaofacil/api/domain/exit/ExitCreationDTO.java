package com.gestaofacil.api.domain.exit;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExitCreationDTO(@NotNull BigDecimal value, @NotNull String description,@NotNull ExitTypeEnum type, @NotNull LocalDateTime exit_date, @NotNull Long company_id) {
}
