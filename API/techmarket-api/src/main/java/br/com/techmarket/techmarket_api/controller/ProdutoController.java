package br.com.techmarket.techmarket_api.controller;

import br.com.techmarket.techmarket_api.model.Produto;
import br.com.techmarket.techmarket_api.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    // @Autowired é opcional
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarTodos();
        // return new ResponseEntity<>(produtos, HttpStatus.OK); -- Maneira extensa
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Integer id) {
        Produto produtos = produtoService.buscarPorId(id);
        // return new ResponseEntity<>(produtos, HttpStatus.OK); -- Maneira extensa
        if(produtos == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.cadastrarProduto(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);

        if(produtoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produtoAtualizado);
        // return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> removerProduto(@PathVariable Integer id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
