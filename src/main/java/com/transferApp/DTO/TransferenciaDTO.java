package com.transferApp.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransferenciaDTO {

    @NotBlank(message = "Conta destino é obrigatória")
    @Pattern(regexp = "\\d{10}", message = "A conta deve ter exatamente 10 dígitos numéricos")
    private String contaDestino;

    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "O valor deve ser positivo")
    private BigDecimal valor;

    @NotNull(message = "Data de transferência é obrigatória")
    private LocalDate dataTransferencia;


}
