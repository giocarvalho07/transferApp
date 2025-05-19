package com.transferApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "A conta deve ter exatamente 10 dígitos numéricos")
    private String contaOrigem = "0000000000"; // Alterado para 10 zeros

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "A conta deve ter exatamente 10 dígitos numéricos")
    private String contaDestino;

    @NotNull
    @Positive(message = "O valor deve ser positivo")
    @Digits(integer = 10, fraction = 2, message = "O valor deve ter no máximo 2 casas decimais")
    private BigDecimal valor;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal taxa;

    @NotNull
    @FutureOrPresent(message = "A data de transferência deve ser hoje ou no futuro")
    private LocalDate dataTransferencia;

    @NotNull
    private LocalDate dataAgendamento = LocalDate.now();
}