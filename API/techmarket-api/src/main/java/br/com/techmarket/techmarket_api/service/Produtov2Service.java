package br.com.techmarket.techmarket_api.service;

import br.com.techmarket.techmarket_api.exception.BusinessException;
import br.com.techmarket.techmarket_api.exception.ResourceNotFoundException;
import br.com.techmarket.techmarket_api.model.Produto;
import br.com.techmarket.techmarket_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Produtov2Service {
    private final ProdutoRepository produtoRepository;

    // @Autowired - Opcional atualmente
    public Produtov2Service(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Integer id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado!"));
    }

    public Optional<Produto> buscarPorIdOptional(Integer id) {
        return produtoRepository.findById(id);
    }

    public Produto cadastrarProduto(Produto produto) {
        produtoRepository.findByNomeProdutoIgnoreCase(produto.getNomeProduto())
                .ifPresent(p -> {
                    throw new BusinessException("Já existe um produto com o nome " + p.getNomeProduto());
                });

        return produtoRepository.save(produto);
    }

    public Produto cadastrarProdutoOptionalFuncional(Produto produto) {
        // Busca por um produto com o mesmo nome, ignorando maiúsculas/minúsculas
        Optional<Produto> produtoExistente = produtoRepository.findByNomeProdutoIgnoreCase(produto.getNomeProduto());

        // Se o produto já existir, retorna o produto encontrado.
        // Se não, salva o novo produto e o retorna.
        return produtoExistente.orElseGet(() -> produtoRepository.save(produto));
    }

    public Optional<Produto> cadastrarProdutoOptional(Produto produto) {
        // Verifica se um produto com o mesmo nome já existe
        boolean produtoJaExiste = produtoRepository.findByNomeProdutoIgnoreCase(produto.getNomeProduto()).isPresent();

        if (produtoJaExiste) {
            // Retorna um Optional vazio para indicar que o cadastro não foi realizado
            return Optional.empty();
        }

        // Salva o novo produto e o encapsula em um Optional
        return Optional.of(produtoRepository.save(produto));
    }


    public Produto atualizarProduto(Integer id, Produto novoProduto) {
        Produto produtoExistente = buscarPorId(id);

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
