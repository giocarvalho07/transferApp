package com.transferApp.service;

import com.transferApp.DTO.TransferenciaDTO;
import com.transferApp.model.Transferencia;
import com.transferApp.repository.TransferenciaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public Transferencia agendar(TransferenciaDTO dto, BigDecimal taxa) {
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
