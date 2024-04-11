package com.gestaofacil.api.controllers;

import com.gestaofacil.api.domain.company.CompanyCreationDTO;
import com.gestaofacil.api.domain.company.CompanyDTO;
import com.gestaofacil.api.service.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @GetMapping
    public ResponseEntity<Page<CompanyDTO>> getAllCompanys(Pageable pagination){
        var companys = companyService.getAllCompanys(pagination);
        return ResponseEntity.ok(companys);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable Long id){
        var company = companyService.getCompany(id);
        if(company == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(company);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyCreationDTO company, UriComponentsBuilder uriBuilder){
        var newCompany = companyService.createCompany(company);
        var uri = uriBuilder.path("/company/{id}").buildAndExpand(newCompany.id()).toUri();
        return ResponseEntity.created(uri).body(newCompany);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long id, @RequestBody CompanyCreationDTO company){
        var newCompany = companyService.updateCompany(company, id);
        return ResponseEntity.ok(newCompany);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        var deleted = companyService.deleteCompany(id);
        if(!deleted) return ResponseEntity.ok("Erro ao deletar empresa");
        return ResponseEntity.ok("empresa deletada com sucesso");
    }
}
