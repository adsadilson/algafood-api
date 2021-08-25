package br.com.apssystem.algafood.api.model;

import br.com.apssystem.algafood.domain.enums.StatusPedido;
import br.com.apssystem.algafood.domain.model.ItemPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoModel {

	private Long id;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;

	private EnderecoModel enderecoEntrega;
	private StatusPedido status;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataConfirmacao;
	private LocalDateTime dataCancelamento;
	private LocalDateTime dataEntrega;

	@JsonIncludeProperties(value = {"id", "descricao"})
	private FormaPagtoModel formaPagamento;

	@JsonIncludeProperties(value = {"id", "nome"})
	private RestauranteModel restaurante;

	@JsonIncludeProperties(value = {"id", "nome"})
	private UsuarioModel cliente;

	@JsonIgnore
	private List<ItemPedido> itens = new ArrayList<>();
}
