package com.gestaofacil.api.domain.exit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gestaofacil.api.domain.company.Company;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Exit")
@Table(name = "exits")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Exit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal value;
    @Enumerated(EnumType.STRING)
    private ExitTypeEnum type;
    private LocalDateTime exit_date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public void update(ExitCreationDTO exit){
        if(exit.exit_date() != null) this.exit_date = exit.exit_date();
        if(exit.description() != null) this.description = exit.description();
        if(exit.value() != null) this.value = exit.value();
        if(exit.type() != null) this.type = exit.type();
    }

}
