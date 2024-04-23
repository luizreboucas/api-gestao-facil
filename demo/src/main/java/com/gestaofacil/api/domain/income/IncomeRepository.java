package com.gestaofacil.api.domain.income;

import com.gestaofacil.api.domain.client.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    public Page<Income> findByCompany_id(Pageable pagination, Long id);

}
