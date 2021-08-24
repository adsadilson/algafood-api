package br.com.apssystem.algafood.api.model.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.apssystem.algafood.domain.enums.StatusPedido;
import br.com.apssystem.algafood.domain.model.Endereco;
import br.com.apssystem.algafood.domain.model.FormaPagto;
import br.com.apssystem.algafood.domain.model.ItemPedido;
import br.com.apssystem.algafood.domain.model.Restaurante;
import br.com.apssystem.algafood.domain.model.Usuario;
import lombok.Data;

@Data
public class PedidoInput {

	private Long id;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private Endereco enderecoEntrega;
	private StatusPedido status;
	private LocalDateTime dataEntrega;
	
	@NotNull
	private FormaPagto formaPagto;
	
	@NotNull
	private Restaurante restaurante;
	
	@NotNull
	private Usuario cliente;
	
	private List<ItemPedido> itens = new ArrayList<>();
}
