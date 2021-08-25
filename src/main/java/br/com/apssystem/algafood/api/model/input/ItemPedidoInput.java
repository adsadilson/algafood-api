package br.com.apssystem.algafood.api.model.input;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class ItemPedidoInput {

    @NotNull
    @Valid
    private ProdutoIdInput produto;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    private String obeservcao;

}
