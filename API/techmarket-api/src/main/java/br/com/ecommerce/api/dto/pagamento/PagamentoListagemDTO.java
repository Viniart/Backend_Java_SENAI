package br.com.ecommerce.api.dto.pagamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoListagemDTO {
    private String formaPagamento;
    private String status;
    private OffsetDateTime dataPagamento;
    private Integer pedidoId;
    private BigDecimal valorPedido;
}
