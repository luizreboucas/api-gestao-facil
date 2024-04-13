package com.gestaofacil.api.domain.exit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExitRepository extends JpaRepository<Exit, Long> {

    public Page<Exit> findByCompany_id(Pageable pagination, Long id);
}
