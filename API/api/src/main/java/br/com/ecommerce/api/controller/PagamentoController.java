package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.dto.pagamento.PagamentoCadastroDTO;
import br.com.ecommerce.api.dto.pagamento.PagamentoListagemDTO;
import br.com.ecommerce.api.model.Pagamento;
import br.com.ecommerce.api.model.Produto;
import br.com.ecommerce.api.service.PagamentoService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping
    public ResponseEntity<List<PagamentoListagemDTO>> listarPagamentos() {
        List<PagamentoListagemDTO> pagamentos = pagamentoService.listarTodos();

        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/projection")
    public ResponseEntity<List<PagamentoListagemDTO>> listarPagamentosProjection() {
        List<PagamentoListagemDTO> pagamentos = pagamentoService.listarTodosDtoProjection();

        return ResponseEntity.ok(pagamentos);
    }

    @PostMapping
    public ResponseEntity<Pagamento> cadastrarPagamento(@RequestBody PagamentoCadastroDTO pagamento) {
        Pagamento pagamentoSalvo = pagamentoService.cadastrarPagamento(pagamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoSalvo);
    }
}
