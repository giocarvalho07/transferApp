package com.transferApp.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransferenciaDTO {

    @Schema(description = "Número da conta destino (10 dígitos)", example = "1234567890")
    private String contaDestino;

    @Schema(description = "Valor da transferência", example = "1500.50")
    private BigDecimal valor;

    @Schema(description = "Data da transferência (formato YYYY-MM-DD)", example = "2024-12-31")
    private LocalDate dataTransferencia;

}
