package br.com.urbanswift.api.controller;

import br.com.urbanswift.api.model.Entrega;
import br.com.urbanswift.api.service.EntregaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregas")
@Tag(name = "Entregas", description = "Endpoints para gerenciamento de entregas")
@SecurityRequirement(name = "bearerAuth")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService service) {
        this.entregaService = service;
    }

    // Listar Todas
    @GetMapping
    @Operation(summary = "Lista todas as entregas", description = "Retorna uma lista com todas as entregas cadastradas.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    public ResponseEntity<List<Entrega>> listarEntregas() {
        List<Entrega> entregas = entregaService.listarTodos();
        return ResponseEntity.ok(entregas);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Busca uma entrega por ID", description = "Retorna uma entrega específica com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrega encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Entrega não encontrada para o ID informado")
    })
    public ResponseEntity<?> buscarEntregaPorId(@PathVariable Integer id) {
        Entrega entrega = entregaService.buscarPorId(id);

        if (entrega == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Entrega não encontrada!");
        }

        return ResponseEntity.ok(entrega);
    }

    // Inserir Nova
    @PostMapping
    @Operation(summary = "Solicita uma nova entrega", description = "Adiciona uma nova entrega ao banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entrega solicitada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a solicitação")
    })
    public ResponseEntity<Entrega> solicitarEntrega(@RequestBody Entrega entrega) {
        Entrega novaEntrega = entregaService.cadastrar(entrega);

        if (novaEntrega == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(novaEntrega);
    }

    // Atualizar
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma entrega existente", description = "Altera os dados de uma entrega com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrega atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Entrega não encontrada para o ID informado")
    })
    public ResponseEntity<?> atualizarEntrega(@PathVariable Integer id, @RequestBody Entrega entrega) {
        Entrega entregaAtualizada = entregaService.atualizar(id, entrega);

        if (entregaAtualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Entrega não encontrada!");
        }

        return ResponseEntity.ok(entregaAtualizada);
    }

    // Deletar (ou Cancelar)
    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui/Cancela uma entrega", description = "Remove uma entrega do banco de dados com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrega excluída/cancelada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Entrega não encontrada para o ID informado")
    })
    public ResponseEntity<?> deletarEntrega(@PathVariable Integer id) {
        Entrega entregaDeletada = entregaService.deletar(id);

        if (entregaDeletada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível excluir/cancelar, pois a entrega não foi encontrada.");
        }

        return ResponseEntity.ok(entregaDeletada);
    }
}