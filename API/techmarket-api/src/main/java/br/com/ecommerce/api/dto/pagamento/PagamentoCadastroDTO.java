package br.com.ecommerce.api.dto.pagamento;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class PagamentoCadastroDTO {
    private String formaPagamento;
    private String status;
    private OffsetDateTime dataPagamento;
    private Integer pedidoId;
    private BigDecimal valorPedido;
}
