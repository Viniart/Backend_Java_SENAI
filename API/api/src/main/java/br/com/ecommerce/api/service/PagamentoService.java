package br.com.ecommerce.api.service;

import br.com.ecommerce.api.dto.pagamento.PagamentoCadastroDTO;
import br.com.ecommerce.api.dto.pagamento.PagamentoListagemDTO;
import br.com.ecommerce.api.model.Pagamento;
import br.com.ecommerce.api.model.Pedido;
import br.com.ecommerce.api.repository.PagamentoRepository;
import br.com.ecommerce.api.repository.PedidoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository,  PedidoRepository pedidoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public List<PagamentoListagemDTO> listarTodos()
    {
        List<Pagamento> pagamentos = pagamentoRepository.findAll(Sort.by(Sort.Direction.DESC, "dataPagamento"));

        return pagamentos.stream()
                .map(this::converterParaListagemDTO)
                .collect(Collectors.toList());
    }

    public List<PagamentoListagemDTO> listarTodosDtoProjection() {
        return pagamentoRepository.listarTodosPagamentos();
    }

    public Pagamento cadastrarPagamento(PagamentoCadastroDTO dto)
    {
        Pedido pedidoAssociado = pedidoRepository.findById(dto.getPedidoId()).orElse(null);

        if(pedidoAssociado == null) {
            return null;
        }

        Pagamento novoPagamento = new Pagamento();

        novoPagamento.setDataPagamento(OffsetDateTime.now());
        novoPagamento.setFormaPagamento(dto.getFormaPagamento());
        novoPagamento.setStatus(dto.getStatus());
        novoPagamento.setPedido(pedidoAssociado);

        return pagamentoRepository.save(novoPagamento);
    }

    private PagamentoListagemDTO converterParaListagemDTO(Pagamento pagamento) {
        PagamentoListagemDTO dto = new PagamentoListagemDTO();

        // Mapeamento campo a campo
        dto.setFormaPagamento(pagamento.getFormaPagamento());
        dto.setStatus(pagamento.getStatus());
        dto.setDataPagamento(pagamento.getDataPagamento());
        dto.setPedidoId(pagamento.getPedido().getId()); // Acessa o objeto aninhado
        dto.setValorPedido(pagamento.getPedido().getValorTotal()); // Acessa o objeto aninhado

        return dto;
    }
}
