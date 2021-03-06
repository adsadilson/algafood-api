package br.com.apssystem.algafood.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empresa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Column(length =80, nullable = false)
    private String nome;

    @Column(length = 80)
    private String apelido;

    @Column(name = "cpf_cnpj",length = 18)
    private String cpfCnpj;

    @Column(name = "rg_insc", length = 20)
    private String rgInsc;

    @Column(name = "insc_municipal", length = 20)
    private String inscMunicipal;

    @Column(name = "cnae", length = 25)
    private String cnae;

    @Column(name = "contato", length = 50)
    private String contato;

    @Email
    @Column(name = "email", length = 80)
    private String email;

    @Column(name = "site", length = 80)
    private String site;

    @Column(name = "data_abertura")
    private LocalDate dataAbertura;

    @Column(name = "celular", length = 25)
    private String celular;

    @Column(name = "telefone", length = 25)
    private String telefone;

    @Enumerated
    private Endereco endereco;

    @Column(name = "status", length = 1)
    private boolean status;

    @Column(name = "obs", columnDefinition = "text")
    private String obs;

    @Column(name = "foto", columnDefinition = "text")
    private String foto;

    @Column(name = "atividade", length = 80)
    private String atividade;

}
