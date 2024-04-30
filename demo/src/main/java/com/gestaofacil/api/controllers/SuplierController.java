package com.gestaofacil.api.controllers;

import com.gestaofacil.api.domain.suplier.SuplierDTO;
import com.gestaofacil.api.service.SuplierService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/supliers")
public class SuplierController {
    @Autowired
    private SuplierService suplierService;
    @GetMapping("/{company_id}")
    public ResponseEntity<Page<SuplierDTO>> getAllSupliersFromTheCompany(Pageable pagination, @PathVariable Long company_id){
        var supliers = suplierService.getAllSupliersForACompany(pagination,company_id);
        return ResponseEntity.ok(supliers);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<SuplierDTO> createSuplier(@RequestBody SuplierDTO suplierData, UriComponentsBuilder uriBuilder){
        var suplier = suplierService.createSuplier(suplierData);
        var uri = uriBuilder.path("/supliers/{suplierId}").buildAndExpand(suplier.id()).toUri();
        return ResponseEntity.created(uri).body(suplier);
    }

    @PutMapping("/{suplierId}")
    @Transactional
    public ResponseEntity<SuplierDTO> updateSuplier(@PathVariable Long suplierId, @RequestBody SuplierDTO suplierNewData){
        var suplier = suplierService.updateSuplier(suplierId,suplierNewData);
        return ResponseEntity.ok(suplier);
    }

    @DeleteMapping("/{suplierId}")
    @Transactional
    public ResponseEntity<String> deleteSuplier(@PathVariable Long suplierId){
        Boolean deleted = suplierService.deleteSuplier(suplierId);
        if(!deleted) throw new RuntimeException("Não foi possível excluir o fornecedor");
        return ResponseEntity.ok("Fornecedor excluído com sucesso");
    }

}
