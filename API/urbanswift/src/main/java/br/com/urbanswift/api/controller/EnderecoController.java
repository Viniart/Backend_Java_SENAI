package br.com.urbanswift.api.controller;

import br.com.urbanswift.api.model.Endereco;
import br.com.urbanswift.api.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
@Tag(name = "Endereços", description = "Endpoints para gerenciamento de endereços")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService service) {
        this.enderecoService = service;
    }

    // Listar Todos
    @GetMapping
    @Operation(summary = "Lista todos os endereços", description = "Retorna uma lista com todos os endereços cadastrados.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    public ResponseEntity<List<Endereco>> listarEnderecos() {
        List<Endereco> enderecos = enderecoService.listarTodos();
        return ResponseEntity.ok(enderecos);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Busca um endereço por ID", description = "Retorna um endereço específico com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o ID informado")
    })
    public ResponseEntity<?> buscarEnderecoPorId(@PathVariable Integer id) {
        Endereco endereco = enderecoService.buscarPorId(id);

        if (endereco == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Endereço não encontrado!");
        }

        return ResponseEntity.ok(endereco);
    }

    // Inserir Novo
    @PostMapping
    @Operation(summary = "Cadastra um novo endereço", description = "Adiciona um novo endereço ao banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o cadastro")
    })
    public ResponseEntity<Endereco> inserirEndereco(@RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.cadastrar(endereco);

        if (novoEndereco == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    // Atualizar
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um endereço existente", description = "Altera os dados de um endereço com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o ID informado")
    })
    public ResponseEntity<?> atualizarEndereco(@PathVariable Integer id, @RequestBody Endereco endereco) {
        Endereco enderecoAtualizado = enderecoService.atualizar(id, endereco);

        if (enderecoAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Endereço não encontrado!");
        }

        return ResponseEntity.ok(enderecoAtualizado);
    }

    // Deletar
    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um endereço", description = "Remove um endereço do banco de dados com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o ID informado")
    })
    public ResponseEntity<?> deletarEndereco(@PathVariable Integer id) {
        Endereco enderecoDeletado = enderecoService.deletar(id);

        if (enderecoDeletado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível excluir, pois o endereço não foi encontrado.");
        }

        return ResponseEntity.ok(enderecoDeletado);
    }
}