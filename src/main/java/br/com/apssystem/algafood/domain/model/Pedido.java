package br.com.apssystem.algafood.domain.model;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.domain.enums.StatusPedido;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    @Column(name = "sub_total", precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "taxa_frete", precision = 12, scale = 2)
    private BigDecimal taxaFrete;

    @Column(name = "valor_total", precision = 12, scale = 2)
    private BigDecimal valorTotal;

    @Embedded
    private Endereco enderecoEntrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @CreationTimestamp
    @Column(name = "hora")
    private LocalDateTime hora;

    @Column(name = "data_confirmacao")
    private LocalDateTime dataConfirmacao;

    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;

    @Column(name = "data_entregue")
    private LocalDateTime dataEntregue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagto_id", nullable = false)
    private FormaPagto formaPagto;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    public void calcularValorTotal() {
        getItens().forEach(ItemPedido::calcularPrecoTotal);
        this.subtotal = this.getItens().stream()
                .map(ItemPedido::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = this.subtotal.add(this.taxaFrete);
    }

    public void confirmar(){
        setStatus(StatusPedido.CONFIRMADO);
        setDataConfirmacao(LocalDateTime.now());
    }
    public void entregar(){
        setStatus(StatusPedido.ENTREGUE);
        setDataEntregue(LocalDateTime.now());
    }
    public void cancelar(){
        setStatus(StatusPedido.CANCELADO);
        setDataCancelamento(LocalDateTime.now());
    }
    public void setStatus(StatusPedido novoStatus) {
        if(null != novoStatus) {
            if (!getStatus().naoPodeAlterarParaNovoStatus(novoStatus)) {
                throw new NegocioException(String.format("O pedido de código %s não pode ser alterado de %s para %s ",
                        getCodigo(), getStatus().getDescricao(), novoStatus.getDescricao()));
            }
            this.status = novoStatus;
        }
    }

    @PrePersist
    private void gerarCodigo(){
        String shortId = RandomStringUtils.randomAlphanumeric(10);
        setCodigo(shortId);
    }

    public void definirFrete() {

        setTaxaFrete(getRestaurante().getFrete());
    }

    public void atribuirPedidoAosItens() {

        getItens().forEach(item -> item.setPedido(this));
    }
}
