package com.example.cupom.services;

import com.example.cupom.dtos.CreateCupomRequest;
import com.example.cupom.models.CupomModel;
import com.example.cupom.repositorys.CupomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class CupomService {
    private final CupomRepository repository;

    public CupomService(CupomRepository repository){
        this.repository = repository;
    }
    @Transactional
    public CupomModel create(CreateCupomRequest req) {
        String normalized = req.getCode().replaceAll("[^A-Za-z0-9]", "").toUpperCase();
        if (normalized.length() > 6) normalized = normalized.substring(0,6);
        else if (normalized.length() < 6) normalized = String.format("%-6s", normalized).replace(' ', '0');


        CupomModel c = new CupomModel();
        c.setCode(normalized);
        c.setDescription(req.getDescription());
        c.setDiscountValue(req.getDiscountValue());
        c.setExpirationDate(req.getExpirationDate());
        c.setPublished(req.getPublished() != null && req.getPublished());
        c.setDeleted(false);


        return repository.save(c);
    }


    public CupomModel getById(Long id) {
        return repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Cupom não encontrado"));
    }


    @Transactional
    public void softDelete(Long id) {
        CupomModel c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cupom não encontrado"));
        if (c.isDeleted()) {
            throw new RuntimeException("Cupom já foi deletado");
        }
        c.setDeleted(true);
        c.setDeletedAt(OffsetDateTime.now());
        repository.save(c);
    }

}
