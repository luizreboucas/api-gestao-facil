package com.gestaofacil.api.domain.suplier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SuplierRepository extends JpaRepository<Suplier, Long> {
    Page<Suplier> findAllByCompany_id(Pageable pagination, Long company_id);
}
