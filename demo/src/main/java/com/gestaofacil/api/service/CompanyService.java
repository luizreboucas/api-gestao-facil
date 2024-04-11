package com.gestaofacil.api.service;

import com.gestaofacil.api.domain.company.Company;
import com.gestaofacil.api.domain.company.CompanyCreationDTO;
import com.gestaofacil.api.domain.company.CompanyDTO;
import com.gestaofacil.api.domain.company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public Page<CompanyDTO> getAllCompanys(Pageable pagination){
        return companyRepository.findAll(pagination).map(CompanyDTO::new);
    }

    public CompanyDTO getCompany(Long id){
        if(!companyRepository.existsById(id)) throw new RuntimeException("empresa inexistente");
        var company = companyRepository.findById(id);
        return new CompanyDTO(company.get());
    }

    public CompanyDTO createCompany(CompanyCreationDTO companyDTO){
        var company = new Company(companyDTO);
        companyRepository.save(company);
        return new CompanyDTO(company);
    }

    public CompanyDTO updateCompany(CompanyCreationDTO company, Long id){
        if(!companyRepository.existsById(id)) throw new RuntimeException("Empresa não encontrada");
        var databaseCompany = companyRepository.findById(id).get();
        databaseCompany.update(company);
        return new CompanyDTO(databaseCompany);
    }

    public Boolean deleteCompany(Long id){
        if(!companyRepository.existsById(id)) throw new RuntimeException("empresa inexistente");
        companyRepository.deleteById(id);
        return true;
    }
}
