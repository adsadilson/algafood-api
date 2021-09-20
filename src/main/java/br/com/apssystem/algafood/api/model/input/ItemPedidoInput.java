package br.com.apssystem.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class ItemPedidoInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    @Valid
    private ProdutoIdInput produto;

    @ApiModelProperty(example = "2", required = true)
    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @ApiModelProperty(example = "Menos picante, por favor")
    private String obeservcao;

}
