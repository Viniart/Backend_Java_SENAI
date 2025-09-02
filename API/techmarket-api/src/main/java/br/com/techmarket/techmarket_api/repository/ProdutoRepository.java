package br.com.techmarket.techmarket_api.repository;

import br.com.techmarket.techmarket_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    // findBy<Model> gera automaticamente um método que busca por base essa informação
    Optional<Produto> findByNomeProdutoIgnoreCase(String nomeProduto);
}
