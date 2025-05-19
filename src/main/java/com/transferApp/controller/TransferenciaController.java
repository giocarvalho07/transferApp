package com.transferApp.controller;

import com.transferApp.DTO.TransferenciaDTO;
import com.transferApp.model.Transferencia;
import com.transferApp.service.TaxaService;
import com.transferApp.service.TransferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/transferencias")
@Tag(name = "Transferências", description = "API para agendamento de transferências financeiras")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;
    private final TaxaService taxaService;

    public TransferenciaController(TransferenciaService transferenciaService, TaxaService taxaService) {
        this.transferenciaService = transferenciaService;
        this.taxaService = taxaService;
    }

    @PostMapping
    @Operation(
            summary = "Agendar transferência",
            description = "Calcula a taxa e agenda uma nova transferência",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Transferência agendada com sucesso",
                            content = @Content(schema = @Schema(implementation = Transferencia.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Dados inválidos"
                    )
            }
    )
    public ResponseEntity<Transferencia> agendarTransferencia(@RequestBody TransferenciaDTO dto) {
        BigDecimal taxa = taxaService.calcularTaxa(dto.getValor(), dto.getDataTransferencia());
        Transferencia transferencia = transferenciaService.agendar(dto, taxa);
        return ResponseEntity.ok(transferencia);
    }

    @GetMapping
    @Operation(summary = "Listar transferências", description = "Retorna todas as transferências agendadas")
    public ResponseEntity<List<Transferencia>> listarTransferencias() {
        return ResponseEntity.ok(transferenciaService.listarTodas());
    }

}
