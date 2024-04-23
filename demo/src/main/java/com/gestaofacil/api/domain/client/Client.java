package com.gestaofacil.api.domain.client;

import com.gestaofacil.api.domain.company.Company;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Client")
@Table(name = "clients")
@EqualsAndHashCode(of = "id")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cnpj;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Client(ClientDTO client){
        this.name = client.name();
        this.cnpj = client.cnpj();
    }

    public void update(ClientUpdateDTO client){
        if(client.cnpj() != null) {
            this.cnpj = client.cnpj();
        }
        if(client.name() != null){
            this.name = client.name();
        }
    }
}
