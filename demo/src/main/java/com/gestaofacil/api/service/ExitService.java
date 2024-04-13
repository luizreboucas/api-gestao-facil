package com.gestaofacil.api.service;

import com.gestaofacil.api.domain.company.CompanyRepository;
import com.gestaofacil.api.domain.exit.Exit;
import com.gestaofacil.api.domain.exit.ExitCreationDTO;
import com.gestaofacil.api.domain.exit.ExitDTO;
import com.gestaofacil.api.domain.exit.ExitRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExitService {
    @Autowired
    ExitRepository exitRepository;
    @Autowired
    CompanyRepository companyRepository;

    public Page<ExitDTO> getExitsForCompany (Pageable pagination, Long companyId){
        var exits = exitRepository.findByCompany_id(pagination, companyId);
        return exits.map(ExitDTO::new);
    }

    public ExitDTO createExit(ExitCreationDTO exit){
        if(!companyRepository.existsById(exit.company_id())) throw new RuntimeException("empresa não encontrada");
        var company = companyRepository.findById(exit.company_id()).get();
        var exitDate = getExitDate(exit);
        var newExit = new Exit(null, exit.description(), exit.value(),exit.type(), exitDate,company);
        exitRepository.save(newExit);
        return new ExitDTO(newExit);
    }

    private LocalDateTime getExitDate(ExitCreationDTO exit){
        if(exit.exit_date() == null) {
            return LocalDateTime.now();
        }
        return exit.exit_date();
    }

}
