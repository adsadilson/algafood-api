package br.com.apssystem.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PedidoStatusResumoModel {

    @JsonProperty("statusAtual")
    private String status;
    @ApiModelProperty(example = "2019-10-28 10:10:00")
    private LocalDateTime dataCriacao;
    @ApiModelProperty(example = "2019-10-28 10:20:00")
    private LocalDateTime dataConfirmacao;
    @ApiModelProperty(example = "2019-10-28 10:30:00")
    private LocalDateTime dataEntregue;
    @ApiModelProperty(example = "2019-10-28 12:10:00")
    private LocalDateTime dataCancelamento;
}
