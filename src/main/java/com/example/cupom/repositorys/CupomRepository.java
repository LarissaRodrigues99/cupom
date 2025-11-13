package com.example.cupom.repositorys;

import com.example.cupom.models.CupomModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CupomRepository extends JpaRepository<CupomModel, Long> {
    Optional<CupomModel> findByIdAndDeletedFalse(Long id);
    Optional<CupomModel> findByCodeAndDeletedFalse(String code);
}
