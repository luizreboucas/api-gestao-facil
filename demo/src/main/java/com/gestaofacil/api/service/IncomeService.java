package com.gestaofacil.api.service;

import com.gestaofacil.api.domain.company.CompanyRepository;
import com.gestaofacil.api.domain.income.Income;
import com.gestaofacil.api.domain.income.IncomeDTO;
import com.gestaofacil.api.domain.income.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IncomeService {
    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    CompanyRepository companyRepository;

    public Page<IncomeDTO> getAllIncomesFromCompany(Pageable pagination, Long company_id){
        var incomes = incomeRepository.findByCompany_id(pagination, company_id).map(IncomeDTO::new);
        return incomes;
    }

    public IncomeDTO createIncome(IncomeDTO income){
        if(income.company_id() == null | !companyRepository.existsById(income.company_id())) throw new RuntimeException("company_id inválido");
        var company = companyRepository.findById(income.company_id());
        var newIncome = new Income(income, company.get());
        if(newIncome.getIncome_date() == null){
            newIncome.setIncome_date(LocalDateTime.now());
        }
        incomeRepository.save(newIncome);
        return new IncomeDTO(newIncome);
    }

    public IncomeDTO updateIncome(Long incomeId, IncomeDTO newIncomeData){
        if(!incomeRepository.existsById(incomeId)) throw new RuntimeException("id de income inválido");
        var income = incomeRepository.findById(incomeId).get();
        income.update(newIncomeData);
        return new IncomeDTO(income);
    }

    public Boolean deleteIncome(Long incomeId){
        if(!incomeRepository.existsById(incomeId)) throw new RuntimeException("income não encontrado");
        incomeRepository.deleteById(incomeId);
        return true;
    }
}
