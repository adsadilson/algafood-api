package br.com.apssystem.algafood.api.model.input;

import br.com.apssystem.algafood.domain.enums.StatusPedido;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoInput {

	private Long id;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;

	@NotNull
	@Valid
	private EnderecoInput enderecoEntrega;

	private StatusPedido status;
	private LocalDateTime dataEntrega;
	
	@NotNull
	@Valid
	private FormaPagtoIdInput formaPagto;
	
	@NotNull
	@Valid
	private RestauranteIdInput restaurante;
	
	@NotNull
	@Valid
	private UsuarioIdInput cliente;

	@Size(min = 1)
	@Valid
	private List<ItemPedidoInput> itens = new ArrayList<>();
}
