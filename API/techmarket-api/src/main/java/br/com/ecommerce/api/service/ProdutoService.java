package br.com.ecommerce.api.service;

import br.com.ecommerce.api.model.ItemDoPedido;
import br.com.ecommerce.api.model.Produto;
import br.com.ecommerce.api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Integer id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Integer id, Produto novoProduto) {
        Produto produtoExistente = buscarPorId(id);

        if(produtoExistente == null) {
            return null;
        }

        produtoExistente.setNomeProduto(novoProduto.getNomeProduto());
        produtoExistente.setDescricao(novoProduto.getDescricao());
        produtoExistente.setPreco(novoProduto.getPreco());
        produtoExistente.setEstoqueDisponivel(novoProduto.getEstoqueDisponivel());
        produtoExistente.setImagemUrl(novoProduto.getImagemUrl());

        return produtoRepository.save(produtoExistente);
    }

    public Produto deletarProduto(Integer id) {
        Produto produto = buscarPorId(id);

        if(produto == null) {
            return null;
        }

        produtoRepository.delete(produto);
        return produto;
    }
}
