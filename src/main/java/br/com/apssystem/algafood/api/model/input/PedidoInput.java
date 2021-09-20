package br.com.apssystem.algafood.api.model.input;

import br.com.apssystem.algafood.domain.enums.StatusPedido;
import io.swagger.annotations.ApiModelProperty;
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

	@ApiModelProperty(example = "es5sabder1")
	private String codigo;
	@ApiModelProperty(example = "175.00")
	private BigDecimal subtotal;
	@ApiModelProperty(example = "5.75")
	private BigDecimal taxaFrete;
	@ApiModelProperty(example = "180.75")
	private BigDecimal valorTotal;

	@NotNull
	@Valid
	private EnderecoInput enderecoEntrega;

	@ApiModelProperty(example = "CRIADO")
	private StatusPedido status;
	private LocalDateTime dataEntrega;
	
	@NotNull
	@Valid
	private FormaPagtoIdInput formaPagto;

	@ApiModelProperty(example = "1")
	@NotNull
	@Valid
	private RestauranteIdInput restaurante;

	@ApiModelProperty(example = "1")
	@NotNull
	@Valid
	private UsuarioIdInput cliente;

	@Size(min = 1)
	@Valid
	private List<ItemPedidoInput> itens = new ArrayList<>();
}
