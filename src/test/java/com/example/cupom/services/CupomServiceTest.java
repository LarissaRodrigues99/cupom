package com.example.cupom.services;

import com.example.cupom.models.CupomModel;
import com.example.cupom.repositorys.CupomRepository;
import exceptions.BusinessException;
import exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CupomServiceTest {

    @Mock
    private CupomRepository repository;

    @InjectMocks
    private CupomService service;

    private CupomModel cupom;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        cupom = new CupomModel();
        cupom.setId(1L);
        cupom.setCode("ABC123");
        cupom.setDescription("Desconto de teste");
        cupom.setDiscountValue(BigDecimal.valueOf(10.0));
        cupom.setExpirationDate(OffsetDateTime.now().plusDays(5));
        cupom.setDeleted(false);
    }

    @Test
    void deveLancarErroQuandoCupomNaoForEncontradoParaDelecao() {
        when(repository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> service.softDelete(1L));
        assertEquals("Cupom não encontrado", ex.getMessage());
    }

    @Test
    void deveFazerSoftDeleteComSucesso() {
        when(repository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(cupom));

        service.softDelete(1L);

        assertTrue(cupom.isDeleted());
        verify(repository, times(1)).save(cupom);
    }

    @Test
    void deveLancarErroAoDeletarCupomJaDeletado() {
        cupom.setDeleted(true);
        when(repository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(cupom));

        BusinessException ex = assertThrows(BusinessException.class, () -> service.softDelete(1L));
        assertEquals("O cupom já foi deletado anteriormente", ex.getMessage());
    }
}