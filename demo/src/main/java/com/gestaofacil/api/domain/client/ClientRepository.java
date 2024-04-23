package com.gestaofacil.api.domain.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("""
            select c from Client c
            where c.id in (select distinct i.client.id from Income i where i.company.id = :company_id)
            """)
    Page<Client> getAllClients(Pageable pagination, Long company_id);
}
