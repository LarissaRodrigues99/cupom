package com.example.cupom.repository;

import com.example.cupom.model.CupomModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CupomRepository extends JpaRepository<CupomModel, Long> {
    Optional<CupomModel> findByCodeAndDeletedFalse(String code);
}
