package br.com.apssystem.algafood.domain.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "foto_produto")
public class FotoProduto {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "produto_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Produto produto;

    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    private String descricao;

    @Column(name = "content_type")
    private String contentType;

    private Long tamanho;

    public Long getRestauranteId(){
        if(produto != null){
            return produto.getRestaurante().getId();
        }
        return null;
    }
}
