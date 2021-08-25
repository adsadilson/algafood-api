package br.com.apssystem.algafood.api.model;

import br.com.apssystem.algafood.domain.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PedidoResumoModel {

	private Long id;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private StatusPedido status;
	private LocalDateTime dataCriacao;

	@JsonIgnore
	private EnderecoModel enderecoEntrega;

	//@JsonIncludeProperties(value = {"nome"})
	@JsonIgnore
	private RestauranteModel restaurante;

	//@JsonIncludeProperties(value = {"nome"})
	@JsonIgnore
	private UsuarioModel cliente;

}
