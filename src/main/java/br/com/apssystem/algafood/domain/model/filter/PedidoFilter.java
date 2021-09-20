package br.com.apssystem.algafood.domain.model.filter;

import br.com.apssystem.algafood.api.model.input.EnderecoInput;
import br.com.apssystem.algafood.api.model.input.FormaPagtoIdInput;
import br.com.apssystem.algafood.api.model.input.RestauranteIdInput;
import br.com.apssystem.algafood.api.model.input.UsuarioIdInput;
import br.com.apssystem.algafood.domain.enums.StatusPedido;
import br.com.apssystem.algafood.domain.model.FormaPagto;
import br.com.apssystem.algafood.domain.model.Restaurante;
import br.com.apssystem.algafood.domain.model.Usuario;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PedidoFilter {

    private BigDecimal subtotal;
    private BigDecimal taxaFreteInicio;
    private BigDecimal taxaFreteFim;
    private BigDecimal valorTotalInicio;
    private BigDecimal valorTotalFim;
    private StatusPedido status;
    private LocalDateTime dataCriacaoInicio;
    private LocalDateTime dataCriacaoFim;
    private LocalDateTime dataEntregaInicio;
    private LocalDateTime dataEntregaFim;
    private FormaPagto formaPagtoId;
    @ApiModelProperty(example = "1", value = "ID do restaurante para filtro da pesquisa")
    private Restaurante restauranteId;
    @ApiModelProperty(example = "1", value = "ID do cliente para filtro da pesquisa")
    private Usuario clienteId;          


}
