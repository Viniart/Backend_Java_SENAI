package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.model.ItemDoPedido;
import br.com.ecommerce.api.model.Produto;
import br.com.ecommerce.api.service.ItemDoPedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
public class ItemDoPedidoController {

    private final ItemDoPedidoService itemDoPedidoService;

    public ItemDoPedidoController(ItemDoPedidoService itemDoPedidoService) {
        this.itemDoPedidoService = itemDoPedidoService;
    }

    @GetMapping
    public ResponseEntity<List<ItemDoPedido>> listarItens() {
        List<ItemDoPedido> itens = itemDoPedidoService.listarTodos();

        return ResponseEntity.ok(itens);
    }
}
