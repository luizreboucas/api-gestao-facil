package com.gestaofacil.api.domain.suplier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gestaofacil.api.domain.company.Company;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Table(name = "supliers")
@Entity(name = "Suplier")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Suplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cnpj;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Suplier(SuplierDTO suplier){
        this.cnpj = suplier.cnpj();
        this.name = suplier.name();
    }

    public void update(SuplierDTO suplier){
        if(suplier.cnpj() != null) this.cnpj = suplier.cnpj();
        if(suplier.name() != null) this.name = suplier.name();
    }
}
