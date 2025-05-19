package com.transferApp.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransferenciaDTO {
    @Schema(description = "Número da conta destino (10 dígitos)", example = "1234567890")
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "A conta deve ter exatamente 10 dígitos numéricos")
    private String contaDestino;

    @Schema(description = "Valor da transferência", example = "1500.50")
    @NotNull
    @Positive
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valor;

    @Schema(description = "Data da transferência (formato YYYY-MM-DD)", example = "2025-05-25")
    @NotNull
    @FutureOrPresent
    private LocalDate dataTransferencia;
}