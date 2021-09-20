package br.com.apssystem.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoModel {

    @ApiModelProperty(example = "Carne assada")
    private String nomeArquivo;
    @ApiModelProperty(example = "Carne assada ao ponto")
    private String descricao;
    @ApiModelProperty(example = "png")
    private String contentType;
    @ApiModelProperty(example = "50kb")
    private Long tamanho;
}
