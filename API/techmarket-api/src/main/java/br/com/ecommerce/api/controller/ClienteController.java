package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.model.Cliente;
import br.com.ecommerce.api.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {
    // Controller -> Service
    private final ClienteService clienteService;

    public ClienteController(ClienteService service) {
        clienteService = service;
    }

    // Listar Todos
    @GetMapping
    @Operation(summary = "Lista todos os clientes", description = "Retorna uma lista com todos os clientes cadastrados no banco de dados.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarTodos();

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um cliente por ID", description = "Retorna um cliente específico com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado para o ID informado")
    })
    public ResponseEntity<?> buscarClientePorId(@PathVariable Integer id) {
        Cliente cliente = clienteService.buscarPorId(id);

        if(cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente não encontrado!");
        }

        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo cliente", description = "Adiciona um novo cliente ao banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o cadastro")
    })
    public ResponseEntity<Cliente> inserirCliente(@RequestBody Cliente cliente) {
        Cliente clienteNovo = clienteService.cadastrarCliente(cliente);

        if(clienteNovo == null) {
            return ResponseEntity.badRequest().body(clienteNovo);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Integer id,
                                                    @RequestBody Cliente cliente) {

        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);

        if(clienteAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente não encontrado!");
        }

        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Integer id) {
        Cliente clienteDeletado = clienteService.deleteCliente(id);

        if(clienteDeletado == null) {
            return ResponseEntity.badRequest().body(clienteDeletado);
        }

        return  ResponseEntity.ok(clienteDeletado);
    }
}
