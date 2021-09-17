package br.com.apssystem.algafood.api.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

	@ApiModelProperty(example = "400", position = 1)
	private Integer status;

	@ApiModelProperty(example = "2019-12-01T18:09:02.70844Z", position = 5)
	private LocalDateTime timestamp;

	@ApiModelProperty(example = "https://apssystem.com.br/recurso-nao-encontrado", position = 10)
	private String type;

	@ApiModelProperty(example = "Recurso não encontrado", position = 15)
	private String title;

	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.",
			position = 20)
	private String detail;

	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.",
			position = 25)
	private String userMessage;

	@ApiModelProperty(value = "Lista de objetos ou campos que geraram o erro (opcional)",
			position = 30)
	private List<Field> fields;

	@ApiModel("ObjetoProblema")
	@Getter
	public static class Field {

		@ApiModelProperty(example = "preco")
		private String name;

		@ApiModelProperty(example = "O preço é obrigatório")
		private String message;

		public Field(String name, String message) {
			this.name = name;
			this.message = message;
		}
	}

}
