package com.transferApp.DTO;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransferenciaDTO {

    private String contaDestino;
    private BigDecimal valor;
    private LocalDate dataTransferencia;

}
