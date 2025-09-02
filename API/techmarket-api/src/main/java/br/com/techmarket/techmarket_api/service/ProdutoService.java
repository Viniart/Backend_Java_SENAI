package br.com.techmarket.techmarket_api.service;

import br.com.techmarket.techmarket_api.exception.BusinessException;
import br.com.techmarket.techmarket_api.exception.ResourceNotFoundException;
import br.com.techmarket.techmarket_api.model.Produto;
import br.com.techmarket.techmarket_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
       Optional<Produto> produto = produtoRepository.findById(id);

       if(produto.isEmpty()) {
           return null;
       }

       return produto.get();
       // return produtoRepository.findById(id).orElse(null);
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

    public void deletarProduto(Integer id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }
}
