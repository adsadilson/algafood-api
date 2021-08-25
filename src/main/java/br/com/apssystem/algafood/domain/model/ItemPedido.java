package br.com.apssystem.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "item_pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;

	@Column(name = "preco_total")
	private BigDecimal precoTotal;

	private Integer quantidade;

	private String observacao;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;

    public void calcularPrecoTotal() {
		BigDecimal prcUnitario = this.getPrecoUnitario();
		var qtde = this.getQuantidade();

		if (prcUnitario == null) {
			prcUnitario = BigDecimal.ZERO;
		}

		if (qtde == null) {
			qtde = 0;
		}

		this.setPrecoTotal(prcUnitario.multiply(new BigDecimal(qtde)));
    }
}
