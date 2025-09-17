package br.com.urbanswift.api.controller;

import br.com.urbanswift.api.model.Veiculo;
import br.com.urbanswift.api.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@Tag(name = "Veículos", description = "Endpoints para gerenciamento de veículos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService service) {
        this.veiculoService = service;
    }

    // Listar Todos
    @GetMapping
    @Operation(summary = "Lista todos os veículos", description = "Retorna uma lista com todos os veículos cadastrados.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    public ResponseEntity<List<Veiculo>> listarVeiculos() {
        List<Veiculo> veiculos = veiculoService.listarTodos();
        return ResponseEntity.ok(veiculos);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Busca um veículo por ID", description = "Retorna um veículo específico com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado para o ID informado")
    })
    public ResponseEntity<?> buscarVeiculoPorId(@PathVariable Integer id) {
        Veiculo veiculo = veiculoService.buscarPorId(id);

        if (veiculo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Veículo não encontrado!");
        }

        return ResponseEntity.ok(veiculo);
    }

    // Inserir Novo
    @PostMapping
    @Operation(summary = "Cadastra um novo veículo", description = "Adiciona um novo veículo ao banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veículo cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o cadastro")
    })
    public ResponseEntity<Veiculo> inserirVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.cadastrar(veiculo);

        if (novoVeiculo == null) {
            // Pode ocorrer se, por exemplo, a placa já existir.
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
    }

    // Atualizar
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um veículo existente", description = "Altera os dados de um veículo com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado para o ID informado")
    })
    public ResponseEntity<?> atualizarVeiculo(@PathVariable Integer id, @RequestBody Veiculo veiculo) {
        Veiculo veiculoAtualizado = veiculoService.atualizar(id, veiculo);

        if (veiculoAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Veículo não encontrado!");
        }

        return ResponseEntity.ok(veiculoAtualizado);

    }

    // Deletar
    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um veículo", description = "Remove um veículo do banco de dados com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado para o ID informado")
    })
    public ResponseEntity<?> deletarVeiculo(@PathVariable Integer id) {
        Veiculo veiculoDeletado = veiculoService.deletar(id);

        if (veiculoDeletado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível excluir, pois o veículo não foi encontrado.");
        }

        return ResponseEntity.ok(veiculoDeletado);
    }
}