package com.gestaofacil.api.controllers;

import com.gestaofacil.api.domain.exit.ExitCreationDTO;
import com.gestaofacil.api.domain.exit.ExitDTO;
import com.gestaofacil.api.service.ExitService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/exits")
public class ExitController {
    @Autowired
    ExitService exitService;
    @GetMapping("/{company_id}")
    public ResponseEntity<Page<ExitDTO>> getAllExitsForCompany(Pageable pagination, @PathVariable Long company_id){
        var exits = exitService.getExitsForCompany(pagination, company_id);
        return ResponseEntity.ok(exits);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<ExitDTO> createExit(@RequestBody ExitCreationDTO exit, UriComponentsBuilder uriBuilder){
        var newExit = exitService.createExit(exit);
        var uri = uriBuilder.path("/exits/{id}").buildAndExpand(newExit.id()).toUri();
        return ResponseEntity.created(uri).body(newExit);
    }

    @PutMapping("/{exitId}")
    @Transactional
    public ResponseEntity<ExitDTO> updateExit(@PathVariable Long exitId, @RequestBody ExitCreationDTO newExitData){
        var exit = exitService.updateExit(newExitData, exitId);
        return ResponseEntity.ok(exit);
    }

    @DeleteMapping("/{exitId}")
    @Transactional
    public ResponseEntity<String> deleteExit(@PathVariable Long exitId){
        exitService.deleteExit(exitId);
        return ResponseEntity.ok("saída deletada com sucesso");
    }

}
