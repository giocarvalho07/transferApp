package com.transferApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransferencia;

    @NotBlank
    @NotNull
    @Pattern(regexp = "\\d{10}", message = "A conta deve ter 10 dígitos")
    private String contaOrigem = "XXXXXXXXXX";

    @NotBlank
    @NotNull
    @Pattern(regexp = "\\d{10}", message = "A conta deve ter 10 dígitos")
    private String contaDestino;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Positive
    private BigDecimal taxa;

    @NotNull
    private LocalDate dataTransferencia;

    private LocalDate dataAgendamento = LocalDate.now();
}