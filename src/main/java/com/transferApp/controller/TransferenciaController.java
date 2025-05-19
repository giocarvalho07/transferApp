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
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.validation.constraints.*;


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
    public ResponseEntity<Transferencia> agendarTransferencia(@Valid @RequestBody TransferenciaDTO dto) {
        BigDecimal taxa = taxaService.calcularTaxa(dto.getValor(), dto.getDataTransferencia());
        Transferencia transferencia = transferenciaService.agendar(dto, taxa);
        return ResponseEntity.ok(transferencia);
    }

    @GetMapping
    @Operation(summary = "Listar transferências", description = "Retorna todas as transferências agendadas")
    public ResponseEntity<List<Transferencia>> listarTransferencias() {
        return ResponseEntity.ok(transferenciaService.listarTodas());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleIllegalArgument(IllegalArgumentException ex) {
        return Map.of("error", ex.getMessage());
    }

}
