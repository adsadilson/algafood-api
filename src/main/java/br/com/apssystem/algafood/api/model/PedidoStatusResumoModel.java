package br.com.apssystem.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PedidoStatusResumoModel {

    @JsonProperty("statusAtual")
    private String status;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataConfirmacao;
    private LocalDateTime dataEntregue;
    private LocalDateTime dataCancelamento;
}
