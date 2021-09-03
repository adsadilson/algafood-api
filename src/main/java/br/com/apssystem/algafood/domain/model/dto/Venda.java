package br.com.apssystem.algafood.domain.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Venda {

    private String data;
    private Long totalVendas;
    private BigDecimal totalFaturado;
    private Integer mes;
    private Integer ano;

    public Venda(Integer mes, Long totalVendas, BigDecimal totalFaturado) {
        this.mes = mes;
        this.totalVendas = totalVendas;
        this.totalFaturado = totalFaturado;
    }
    public Venda(Long totalVendas, BigDecimal totalFaturado, Integer ano) {
        this.totalVendas = totalVendas;
        this.totalFaturado = totalFaturado;
        this.ano = ano;
    }

    public Venda(String data, Long totalVendas, BigDecimal totalFaturado) {
        this.data = data;
        this.totalVendas = totalVendas;
        this.totalFaturado = totalFaturado;
    }
}
