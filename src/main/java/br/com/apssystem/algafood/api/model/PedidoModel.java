package br.com.apssystem.algafood.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import br.com.apssystem.algafood.domain.enums.StatusPedido;
import br.com.apssystem.algafood.domain.model.ItemPedido;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "Pedidos")
@Getter
@Setter
public class PedidoModel extends RepresentationModel<PedidoModel> {

	@ApiModelProperty(example = "es5sabder1")
	private String codigo;
	@ApiModelProperty(example = "175.00")
	private BigDecimal subtotal;
	@ApiModelProperty(example = "5.75")
	private BigDecimal taxaFrete;
	@ApiModelProperty(example = "180.75")
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
