package com.transferApp.controller;

import com.transferApp.DTO.TransferenciaDTO;
import com.transferApp.model.Transferencia;
import com.transferApp.service.TransferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transferencias")
@Tag(name = "Transferências", description = "API para agendamento de transferências financeiras")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping
    @Operation(summary = "Agendar transferência", description = "Calcula a taxa e agenda uma nova transferência")
    @ApiResponse(responseCode = "200", description = "Transferência agendada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    public ResponseEntity<Transferencia> agendarTransferencia(@Valid @RequestBody TransferenciaDTO dto) {
        Transferencia transferencia = transferenciaService.agendarTransferencia(dto);
        return ResponseEntity.ok(transferencia);
    }

    @GetMapping
    @Operation(summary = "Listar transferências", description = "Retorna todas as transferências agendadas")
    public ResponseEntity<List<Transferencia>> listarTransferencias() {
        return ResponseEntity.ok(transferenciaService.listarTodas());
    }
}