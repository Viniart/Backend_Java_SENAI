package br.com.ecommerce.api.repository;

import br.com.ecommerce.api.dto.pagamento.PagamentoListagemDTO;
import br.com.ecommerce.api.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    @Query("SELECT NEW br.com.ecommerce.api.dto.pagamento.PagamentoListagemDTO(" +
            "p.formaPagamento, " +
            "p.status, " +
            "p.dataPagamento, " +
            "p.pedido.id, " +
            "p.pedido.valorTotal " +
            ") FROM Pagamento p")
    List<PagamentoListagemDTO> listarTodosPagamentos();
}
