package com.gestaofacil.api.controllers;

import com.gestaofacil.api.domain.income.IncomeDTO;
import com.gestaofacil.api.domain.income.IncomeRepository;
import com.gestaofacil.api.service.IncomeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/incomes")
public class IncomeController {
    @Autowired
    IncomeService incomeService;
    @GetMapping("/{companyId}")
    public ResponseEntity<Page<IncomeDTO>> getIncomesFromCompany(@PathVariable Long companyId, Pageable pagination){
        var incomes = incomeService.getAllIncomesFromCompany(pagination, companyId);
        return ResponseEntity.ok(incomes);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<IncomeDTO> createIncome (@RequestBody IncomeDTO incomeData, UriComponentsBuilder uriBuilder){
        var income = incomeService.createIncome(incomeData);
        var uri = uriBuilder.path("/incomes/{income.id}").buildAndExpand(income.id()).toUri();
        return ResponseEntity.created(uri).body(income);
    }

    @PutMapping("/{incomeId}")
    @Transactional
    public ResponseEntity<IncomeDTO> updateIncome(@PathVariable Long incomeId, @RequestBody IncomeDTO incomeData){
        var income = incomeService.updateIncome(incomeId,incomeData);
        return ResponseEntity.ok(income);
    }

    @DeleteMapping("/{incomeId}")
    @Transactional
    public ResponseEntity<String> deleteIncome(@PathVariable Long incomeId){
        var deleted = incomeService.deleteIncome(incomeId);
        if(!deleted) throw new RuntimeException("não foi possível excluir o income selecionado");
        return ResponseEntity.ok("income deletado com sucesso");
    }
}
