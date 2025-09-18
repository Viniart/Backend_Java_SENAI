package br.com.urbanswift.api.controller;

import br.com.urbanswift.api.model.TipoUsuario;
import br.com.urbanswift.api.service.TipoUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@Tag(name = "Tipos de Usuário", description = "Endpoints para gerenciamento de tipos de usuário")
@SecurityRequirement(name = "bearerAuth")
public class TipoUsuarioController {

    private final TipoUsuarioService tipoUsuarioService;

    public TipoUsuarioController(TipoUsuarioService service) {
        this.tipoUsuarioService = service;
    }

    // Listar Todos
    @GetMapping
    @Operation(summary = "Lista todos os tipos de usuário", description = "Retorna uma lista com todos os tipos de usuário cadastrados.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    public ResponseEntity<List<TipoUsuario>> listarTiposUsuario() {
        List<TipoUsuario> tiposUsuario = tipoUsuarioService.listarTodos();
        return ResponseEntity.ok(tiposUsuario);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Busca um tipo de usuário por ID", description = "Retorna um tipo de usuário específico com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado para o ID informado")
    })
    public ResponseEntity<?> buscarTipoUsuarioPorId(@PathVariable Integer id) {
        TipoUsuario tipoUsuario = tipoUsuarioService.buscarPorId(id);

        if (tipoUsuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tipo de usuário não encontrado!");
        }

        return ResponseEntity.ok(tipoUsuario);
    }

    // Inserir Novo
    @PostMapping
    @Operation(summary = "Cadastra um novo tipo de usuário", description = "Adiciona um novo tipo de usuário ao banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o cadastro")
    })
    public ResponseEntity<TipoUsuario> inserirTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario novoTipoUsuario = tipoUsuarioService.cadastrar(tipoUsuario);

        if (novoTipoUsuario == null) {
            return ResponseEntity.badRequest().build(); // Retorna 400 sem corpo se a entrada for inválida
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(novoTipoUsuario);
    }

    // Atualizar
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um tipo de usuário existente", description = "Altera os dados de um tipo de usuário com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado para o ID informado")
    })
    public ResponseEntity<?> atualizarTipoUsuario(@PathVariable Integer id, @RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario tipoUsuarioAtualizado = tipoUsuarioService.atualizar(id, tipoUsuario);

        if (tipoUsuarioAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tipo de usuário não encontrado!");
        }

        return ResponseEntity.ok(tipoUsuarioAtualizado);
    }

    // Deletar
    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um tipo de usuário", description = "Remove um tipo de usuário do banco de dados com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado para o ID informado")
    })
    public ResponseEntity<?> deletarTipoUsuario(@PathVariable Integer id) {
        TipoUsuario deletado = tipoUsuarioService.deletar(id);

        if (deletado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível excluir, pois o tipo de usuário não foi encontrado.");
        }

        return ResponseEntity.ok("Tipo de usuário excluído com sucesso!");
    }
}