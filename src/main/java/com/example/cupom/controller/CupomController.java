package com.example.cupom.controller;

import com.example.cupom.dtos.CreateCupomRequest;
import com.example.cupom.models.CupomModel;
import com.example.cupom.services.CupomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cupons")
public class CupomController {

    private final CupomService service;


    public CupomController(CupomService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CupomModel> create(@Valid @RequestBody CreateCupomRequest request) {
        CupomModel c = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CupomModel> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }

}
