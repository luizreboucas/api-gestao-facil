package com.gestaofacil.api.domain.income;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    public Page<Income> findByCompany_id(Pageable pagination, Long id);
}
