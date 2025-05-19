package com.transferApp.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class TaxaService {

    private static final BigDecimal TAXA_DIA_ZERO_PORCENTAGEM = new BigDecimal("0.025");
    private static final BigDecimal TAXA_DIA_ZERO_VALOR_FIXO = new BigDecimal("3.00");
    private static final BigDecimal TAXA_1_A_10_DIAS = new BigDecimal("12.00");
    private static final BigDecimal TAXA_11_A_20_DIAS_PORCENTAGEM = new BigDecimal("0.082");
    private static final BigDecimal TAXA_21_A_30_DIAS_PORCENTAGEM = new BigDecimal("0.069");
    private static final BigDecimal TAXA_31_A_40_DIAS_PORCENTAGEM = new BigDecimal("0.047");
    private static final BigDecimal TAXA_41_A_50_DIAS_PORCENTAGEM = new BigDecimal("0.017");

    public BigDecimal calcularTaxa(BigDecimal valor, LocalDate dataTransferencia) {
        validarParametros(valor, dataTransferencia);

        long dias = ChronoUnit.DAYS.between(LocalDate.now(), dataTransferencia);

        if (dias == 0) {
            return calcularTaxaDiaZero(valor);
        } else if (dias >= 1 && dias <= 10) {
            return TAXA_1_A_10_DIAS;
        } else if (dias >= 11 && dias <= 20) {
            return calcularTaxaPercentual(valor, TAXA_11_A_20_DIAS_PORCENTAGEM);
        } else if (dias >= 21 && dias <= 30) {
            return calcularTaxaPercentual(valor, TAXA_21_A_30_DIAS_PORCENTAGEM);
        } else if (dias >= 31 && dias <= 40) {
            return calcularTaxaPercentual(valor, TAXA_31_A_40_DIAS_PORCENTAGEM);
        } else if (dias >= 41 && dias <= 50) {
            return calcularTaxaPercentual(valor, TAXA_41_A_50_DIAS_PORCENTAGEM);
        }

        throw new IllegalArgumentException("Não há taxa aplicável para transferências com mais de 50 dias de antecedência");
    }

    private void validarParametros(BigDecimal valor, LocalDate dataTransferencia) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor da transferência deve ser positivo");
        }

        if (dataTransferencia == null) {
            throw new IllegalArgumentException("Data de transferência não pode ser nula");
        }

        if (dataTransferencia.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de transferência não pode ser no passado");
        }
    }

    private BigDecimal calcularTaxaDiaZero(BigDecimal valor) {
        return valor.multiply(TAXA_DIA_ZERO_PORCENTAGEM)
                .add(TAXA_DIA_ZERO_VALOR_FIXO)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calcularTaxaPercentual(BigDecimal valor, BigDecimal percentual) {
        return valor.multiply(percentual)
                .setScale(2, RoundingMode.HALF_UP);
    }
}