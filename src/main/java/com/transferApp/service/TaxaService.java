package com.transferApp.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class TaxaService {

    public BigDecimal calcularTaxa(BigDecimal valor, LocalDate dataTransferencia) {
        LocalDate hoje = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(hoje, dataTransferencia);

        if (dias == 0) {
            return valor.multiply(BigDecimal.valueOf(0.025)).add(BigDecimal.valueOf(3));
        } else if (dias >= 1 && dias <= 10) {
            return BigDecimal.valueOf(12);
        } else if (dias >= 11 && dias <= 20) {
            return valor.multiply(BigDecimal.valueOf(0.082));
        } else if (dias >= 21 && dias <= 30) {
            return valor.multiply(BigDecimal.valueOf(0.069));
        } else if (dias >= 31 && dias <= 40) {
            return valor.multiply(BigDecimal.valueOf(0.047));
        } else if (dias >= 41 && dias <= 50) {
            return valor.multiply(BigDecimal.valueOf(0.017));
        }

        throw new IllegalArgumentException("Não há taxa aplicável para esta data de transferência");
    }
}
