package com.gestaofacil.api.domain.suplier;

public record SuplierDTO (Long id, String name, String cnpj, Long company_id){

    public SuplierDTO(Suplier suplier){
        this(suplier.getId(),suplier.getName(), suplier.getCnpj(), suplier.getCompany().getId());
    }
}
