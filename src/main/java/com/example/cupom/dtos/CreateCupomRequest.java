package com.example.cupom.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class CreateCupomRequest {
    @NotBlank
    private String code;


    @NotBlank
    private String description;


    @NotNull
    @DecimalMin(value = "0.5", inclusive = true)
    private BigDecimal discountValue;


    @NotNull
    @FutureOrPresent
    private OffsetDateTime expirationDate;


    private Boolean published = false;

    public @NotBlank String getCode() {
        return code;
    }

    public void setCode(@NotBlank String code) {
        this.code = code;
    }

    public @NotBlank String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank String description) {
        this.description = description;
    }

    public @NotNull @DecimalMin(value = "0.5", inclusive = true) BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(@NotNull @DecimalMin(value = "0.5", inclusive = true) BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public @NotNull @FutureOrPresent OffsetDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(@NotNull @FutureOrPresent OffsetDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}
