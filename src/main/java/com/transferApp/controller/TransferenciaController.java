package com.transferApp.controller;

import com.transferApp.DTO.TransferenciaDTO;
import com.transferApp.model.Transferencia;
import com.transferApp.service.TaxaService;
import com.transferApp.service.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;
    private final TaxaService taxaService;

    public TransferenciaController(TransferenciaService transferenciaService, TaxaService taxaService) {
        this.transferenciaService = transferenciaService;
        this.taxaService = taxaService;
    }

    @PostMapping
    public ResponseEntity<Transferencia> agendarTransferencia(@RequestBody TransferenciaDTO dto) {
        BigDecimal taxa = taxaService.calcularTaxa(dto.getValor(), dto.getDataTransferencia());
        Transferencia transferencia = transferenciaService.agendar(dto, taxa);
        return ResponseEntity.ok(transferencia);
    }

    @GetMapping
    public ResponseEntity<List<Transferencia>> listarTransferencias() {
        return ResponseEntity.ok(transferenciaService.listarTodas());
    }

}
