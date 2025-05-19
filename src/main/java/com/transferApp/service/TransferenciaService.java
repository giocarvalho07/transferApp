package com.transferApp.service;

import com.transferApp.DTO.TransferenciaDTO;
import com.transferApp.model.Transferencia;
import com.transferApp.repository.TransferenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final TaxaService taxaService;

    public TransferenciaService(TransferenciaRepository transferenciaRepository, TaxaService taxaService) {
        this.transferenciaRepository = transferenciaRepository;
        this.taxaService = taxaService;
    }

    @Transactional
    public Transferencia agendarTransferencia(TransferenciaDTO dto) {
        BigDecimal taxa = taxaService.calcularTaxa(dto.getValor(), dto.getDataTransferencia());

        Transferencia transferencia = new Transferencia();
        transferencia.setContaDestino(dto.getContaDestino());
        transferencia.setValor(dto.getValor());
        transferencia.setTaxa(taxa);
        transferencia.setDataTransferencia(dto.getDataTransferencia());

        return transferenciaRepository.save(transferencia);
    }

    public List<Transferencia> listarTodas() {
        return transferenciaRepository.findAll();
    }
}