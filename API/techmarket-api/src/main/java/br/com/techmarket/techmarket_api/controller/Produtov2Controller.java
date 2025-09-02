package br.com.techmarket.techmarket_api.controller;

import br.com.techmarket.techmarket_api.model.Produto;
import br.com.techmarket.techmarket_api.service.Produtov2Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtosv2")
public class Produtov2Controller {
    private final Produtov2Service produtov2Service;

    // @Autowired é opcional
    public Produtov2Controller(Produtov2Service produtov2Service) {
        this.produtov2Service = produtov2Service;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtov2Service.listarTodos();
        // return new ResponseEntity<>(produtos, HttpStatus.OK); -- Maneira extensa
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Integer id) {
        Produto produtos = produtov2Service.buscarPorId(id);
        // return new ResponseEntity<>(produtos, HttpStatus.OK); -- Maneira extensa
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/optional/{id}")
    public ResponseEntity<Produto> buscarProdutoPorIdOptional(@PathVariable Integer id) {
        Optional<Produto> produtoOptional = produtov2Service.buscarPorIdOptional(id);

        if(produtoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoOptional.get());
    }


    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtov2Service.cadastrarProduto(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        produtov2Service.atualizarProduto(id, produto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> removerProduto(@PathVariable Integer id) {
        produtov2Service.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/cadastro-optional")
    public ResponseEntity<?> cadastrarProdutoOptional(@RequestBody Produto produto) {
        // 1. Chama o serviço que tenta cadastrar o produto
        Optional<Produto> produtoSalvoOptional = produtov2Service.cadastrarProdutoOptional(produto);

        // 2. Verifica se o Optional contém um valor (se o produto foi salvo)
        if (produtoSalvoOptional.isPresent()) {
            // Se sim, o produto foi criado com sucesso.
            Produto produtoSalvo = produtoSalvoOptional.get();

            // Cria a URL de localização do novo recurso
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(produtoSalvo.getId())
                    .toUri();

            // Retorna HTTP 201 Created com a localização e o produto no corpo
            return ResponseEntity.created(location).body(produtoSalvo);
        } else {
            // Se não, o Optional está vazio, significando que já existe um produto com esse nome.
            String mensagemErro = "Produto com o nome '" + produto.getNomeProduto() + "' já existe.";

            // Retorna HTTP 409 Conflict com uma mensagem de erro
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mensagemErro);
        }
    }
}
