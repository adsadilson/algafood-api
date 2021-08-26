package br.com.apssystem.algafood.domain.enums;

import java.util.List;

public enum StatusPedido {

	CRIADO("Criado"), 
	CONFIRMADO("Confirmado", CRIADO),
	ENTREGUE("Entregue", CONFIRMADO),
	CANCELADO("Cancelado", CRIADO);

	private String descricao;
	private List<StatusPedido> statusAnteriores;

	StatusPedido(String descricao, StatusPedido... statusPedidos) {
		this.descricao = descricao;
		this.statusAnteriores = List.of(statusPedidos);
	}

	public String getDescricao() {
		return descricao;
	}

	public boolean naoPodeAlterarParaNovoStatus(StatusPedido novoStatus){
		return novoStatus.statusAnteriores.contains(this);

	}

}
