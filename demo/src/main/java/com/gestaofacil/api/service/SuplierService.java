package com.gestaofacil.api.service;

import com.gestaofacil.api.domain.company.CompanyRepository;
import com.gestaofacil.api.domain.suplier.Suplier;
import com.gestaofacil.api.domain.suplier.SuplierDTO;
import com.gestaofacil.api.domain.suplier.SuplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SuplierService {
    @Autowired
    private SuplierRepository suplierRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public Page<SuplierDTO> getAllSupliersForACompany(Pageable pagination, Long company_id){
        var supliers = suplierRepository.findAllByCompany_id(pagination, company_id);
        return supliers.map(SuplierDTO::new);
    }

    public SuplierDTO createSuplier(SuplierDTO suplierData){
        if(!companyRepository.existsById(suplierData.company_id())) throw new RuntimeException("empresa não localizada");
        var company = companyRepository.findById(suplierData.company_id()).get();
        var suplier = new Suplier(null,suplierData.name(),suplierData.cnpj(),company);
        suplierRepository.save(suplier);
        return new SuplierDTO(suplier);
    }

    public SuplierDTO updateSuplier(Long suplierId, SuplierDTO suplierNewData){
        if(!suplierRepository.existsById(suplierId)) throw new RuntimeException("fornecedor não localizado");
        var suplier = suplierRepository.findById(suplierId).get();
        suplier.update(suplierNewData);
        return new SuplierDTO(suplier);
    }

    public Boolean deleteSuplier(Long suplierId){
        if(!suplierRepository.existsById(suplierId)) throw new RuntimeException("fornecedor não localizado");
        suplierRepository.deleteById(suplierId);
        return true;
    }
}
